package com.code.challenge.employee.api.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.code.challenge.employee.api.dao.EmployeeRepository;
import com.code.challenge.employee.api.model.Employee;
import com.code.challenge.employee.api.model.Hobby;
import com.code.challenge.employee.api.responses.ApiResult;
import com.code.challenge.employee.api.utils.EmployeeValdation;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Value("${rest.endpoint}")
	String restEndPoint;

	@Override
	public ResponseEntity getEmployees(int pageNumber, int pageSize) {
		String calledService = "list all employees";
		EmployeeValdation employeeValdation = new EmployeeValdation(calledService);
		String nextPageUrl = null;
		String previousPageUrl = null;

		Page<Employee> emloyeesPage = employeeRepository.findAll(PageRequest.of(pageNumber, pageSize));
		int totalCount = (int) emloyeesPage.getTotalElements();
		int pagetotal = (int) (totalCount / pageSize) + 1;
		if (pageNumber + 1 < pagetotal) {
			nextPageUrl = restEndPoint + "&pageSize=" + pageSize + "&pageNumber=" + (pageNumber + 1);
		}

		if (pageNumber + 1 > 1) {
			previousPageUrl = restEndPoint + "&pageSize=" + pageSize + "&pageNumber=" + (pageNumber - 1);
		}

		return ResponseEntity.status(200)
				.body(employeeValdation.getNewApiResponse(HttpStatus.OK, 200, calledService, new ApiResult(totalCount,
						emloyeesPage.getTotalPages(), nextPageUrl, previousPageUrl, emloyeesPage.getContent())));
	}

	@Override
	public ResponseEntity save(String fullName, String email, String birthday, List<String> hobbies) {

		Set<Hobby> hobbySet = new HashSet<>();

		/** employee validations notnull valid email and birthday */

		String calledService = "create an employee";
		EmployeeValdation employeeValdation = new EmployeeValdation(calledService);
		employeeValdation.ValidInput(fullName, email, birthday);

		if (!employeeValdation.isValid())
			return employeeValdation.getResponseEntity();

		Employee employeeDB = employeeRepository.getEmployeeByEmail(email);
		if (employeeDB != null) {
			return ResponseEntity.status(400).body(employeeValdation.getNewApiResponse(HttpStatus.BAD_REQUEST, 400,
					calledService, email + " employee email already exist !!"));
		}
		/***********/
		Employee employee = employeeValdation.getEmployee();
		hobbies.stream().forEach(e -> hobbySet.add(new Hobby(e, employee)));

		employee.setHobbies(hobbySet);
		employeeDB = employeeRepository.save(employee);
		if (employeeDB != null)
			return ResponseEntity.status(201).body(employeeValdation.getNewApiResponse(HttpStatus.CREATED, 201,
					calledService, employeeDB.getUuid().toString() + " was created successfully"));
		return null;
	}

	@Override
	public ResponseEntity getEmployeeByUUID(String uuid) {

		/** employee validations invalid uuid , Employee not found */

		String calledService = "get employee by uuid";
		EmployeeValdation employeeValdation = new EmployeeValdation(calledService);
		employeeValdation.valideUUID(uuid);
		if (!employeeValdation.isValid())
			return employeeValdation.getResponseEntity();
		Employee employee = employeeRepository.getEmployeeByUUID(UUID.fromString(uuid));
		if (employee == null)
			return ResponseEntity.status(404).body(employeeValdation.getNewApiResponse(HttpStatus.NOT_FOUND, 404,
					calledService, uuid + " Employee not found"));

		/***********/
		return ResponseEntity.status(302)
				.body(employeeValdation.getNewApiResponse(HttpStatus.FOUND, 302, calledService, employee));
	}

	@Override
	public ResponseEntity deleteByUUID(String uuid) {

		/** employee validations invalid uuid , Employee not found */

		String calledService = "delete employee by uuid";
		EmployeeValdation employeeValdation = new EmployeeValdation(calledService);
		employeeValdation.valideUUID(uuid);
		if (!employeeValdation.isValid())
			return employeeValdation.getResponseEntity();
		Employee employee = employeeRepository.getEmployeeByUUID(UUID.fromString(uuid));
		if (employee == null)
			return ResponseEntity.status(404).body(employeeValdation.getNewApiResponse(HttpStatus.NOT_FOUND, 404,
					calledService, uuid + " Employee not found"));

		/***********/

		employeeRepository.delete(employee);
		return ResponseEntity.status(200).body(employeeValdation.getNewApiResponse(HttpStatus.OK, 200, calledService,
				uuid + " was deleted successfully"));
	}

	@Override
	public ResponseEntity update(String uuid, String fullName, String email, String birthday) {

		/** employee validations invalid uuid , Employee not found */

		String calledService = "update employee by uuid";
		EmployeeValdation employeeValdation = new EmployeeValdation(calledService);
		employeeValdation.valideUUID(uuid);
		if (!employeeValdation.isValid())
			return employeeValdation.getResponseEntity();
		Employee employee = employeeRepository.getEmployeeByUUID(UUID.fromString(uuid));
		if (employee == null)
			return ResponseEntity.status(404).body(employeeValdation.getNewApiResponse(HttpStatus.NOT_FOUND, 404,
					calledService, uuid + " Employee not found"));
		/***********/

		employee.setEmail(email);
		employee.setFullName(fullName);
		employee.setBirthday(LocalDate.parse(birthday));
		employeeRepository.save(employee);
		return ResponseEntity.status(200).body(employeeValdation.getNewApiResponse(HttpStatus.OK, 200, calledService,
				uuid + " was update successfully"));
	}

}
