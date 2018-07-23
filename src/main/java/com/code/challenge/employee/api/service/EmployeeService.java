package com.code.challenge.employee.api.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
	ResponseEntity getEmployees(int page, int perPage);

	ResponseEntity getEmployeeByUUID(String uuid);

	ResponseEntity save(String fullName, String email, String birthday, List<String> hobbies);

	ResponseEntity deleteByUUID(String uuid);

	ResponseEntity update(String uuid, String fullName, String email, String birthday);

}
