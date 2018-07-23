package com.code.challenge.employee.api.utils;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.code.challenge.employee.api.model.Employee;
import com.code.challenge.employee.api.responses.ApiResponse;
import com.code.challenge.employee.api.responses.ResponseMessage;

public class EmployeeValidation {

	private boolean isValid = true;
	private String calledService;

	private Employee employee;
	private ResponseEntity responseEntity;

	public EmployeeValidation(String calledService) {
		this.calledService = calledService;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void ValidInput(String fullName, String email, String birthday) {
		if (!isValidDate(birthday)) {
			isValid = false;
			responseEntity = ResponseEntity.status(400).body(
					getNewApiResponse(HttpStatus.BAD_REQUEST, 400, calledService, birthday + " is not a valid date "));
			return;
		}

		this.employee = new Employee(fullName, email, LocalDate.parse(birthday));

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
		if (constraintViolations.size() == 1) {
			responseEntity = ResponseEntity.status(400).body(getNewApiResponse(HttpStatus.BAD_REQUEST, 400,
					calledService, constraintViolations.iterator().next().getMessage()));
			isValid = false;
		}
	}

	public void valideUUID(String uuid) {
		isValid = isValidUUID(uuid);
		if (!isValid)
			responseEntity = ResponseEntity.status(400).body(
					getNewApiResponse(HttpStatus.BAD_REQUEST, 400, calledService, uuid + " is not a valid  uuid "));

	}

	private boolean isValidUUID(String uuid) {

		try {
			UUID.fromString(uuid);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	private  boolean isValidDate(String date) {

		try {
			LocalDate.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public ApiResponse getNewApiResponse(HttpStatus httpStatus, int httpStatusCode, String method,
			String responseMessage) {
		return new ApiResponse(httpStatus, httpStatusCode, method, new ResponseMessage(responseMessage));

	}

	public ApiResponse getNewApiResponse(HttpStatus httpStatus, int httpStatusCode, String method, Object object) {
		return new ApiResponse(httpStatus, httpStatusCode, method, object);

	}

	public ResponseEntity getResponseEntity() {
		return responseEntity;
	}

}
