package com.code.challenge.employee.api.controllers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.challenge.employee.api.dao.EmployeeRepository;
import com.code.challenge.employee.api.model.Employee;
import com.code.challenge.employee.api.model.Hobby;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/employee-api")
@Api(tags = { "employee-api Resourse" })
@SwaggerDefinition(tags = { @Tag(name = "Employee Rest API", description = "Employee API ­ Coding Challenge") })
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@ApiOperation(value = "Create an employee")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Employee was created successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/v1/employees", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity createEmployee(
			@ApiParam(value = "E­mail address", required = true) @RequestParam("email") String email,
			@ApiParam(value = "Full name (first and last name)", required = true) @RequestParam("fullName") String fullName,
			@ApiParam(value = "Birthday  YYYY­-MM­-DD)", required = false) @RequestParam("birthday") String birthday,
			@ApiParam(value = "List of hobbies (soccer, music, etc)", required = false) @RequestParam("hobbies") List<String> hobbies) {

		// TODO add validation for email and birthday

		Employee employee = new Employee(fullName, email, LocalDate.parse(birthday));
		Set<Hobby> hobbySet = new HashSet<>();
		hobbies.stream().forEach(e -> hobbySet.add(new Hobby(e, employee)));

		employee.setHobbies(hobbySet);
		Employee employeeDB = employeeRepository.save(employee);
		if (employeeDB != null)
			return ResponseEntity.status(201).body(employeeDB.getUuid().toString() + " was created successfully");

		return null;
	}

	@ApiOperation(value = "View a list of all employee ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/v1/employees", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getAllEmployees(
			@ApiParam(value = "How many employees to return at once; 1000 maximum.", defaultValue = "1000", allowableValues = "range[1, 1000]") @RequestParam("pageSize") int pageSize,
			@ApiParam(value = "The page of the result set.", defaultValue = "1", allowableValues = "range[1, 1000]") @RequestParam("pageNumber") String pageNumber,
			Model model) {
		return ResponseEntity.ok(employeeRepository.findAll());
	}

	@ApiOperation(value = "Find an employee by uuid")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Employee uuid"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Employee not found") })
	@RequestMapping(value = "/v1/employees/{uuid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getEmployeeByUUID(
			@ApiParam(value = "Employee uuid", required = true) @PathVariable(value = "uuid") String uuid) {
		return ResponseEntity.ok(null);
	}

	@ApiOperation(value = "Update an employee by uuid")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Employee uuid"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Employee not found") })
	@RequestMapping(value = "/v1/employees/{uuid}", method = RequestMethod.PUT, produces = "application/json")
	public String UpdateEmployee(
			@ApiParam(value = "Employee uuid to delete", required = true) @PathVariable(value = "uuid") String uuid) {
		return "success";
	}

	@ApiOperation(value = "Delete an employee by uuid")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Employee uuid"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Employee not found") })
	@RequestMapping(value = "/v1/employees/{uuid}", method = RequestMethod.DELETE, produces = "application/json")
	public String delete(
			@ApiParam(value = "Employee uuid to delete", required = true) @PathVariable(value = "uuid") String uuid) {
		return "success";
	}
}
