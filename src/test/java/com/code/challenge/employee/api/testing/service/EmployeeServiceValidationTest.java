package com.code.challenge.employee.api.testing.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.code.challenge.employee.api.EmployeeApiApplication;
import com.code.challenge.employee.api.dao.EmployeeRepository;
import com.code.challenge.employee.api.model.Employee;
import com.code.challenge.employee.api.model.Hobby;
import com.code.challenge.employee.api.responses.ApiResponse;
import com.code.challenge.employee.api.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeApiApplication.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
@ActiveProfiles("dev")
public class EmployeeServiceValidationTest {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * expected all validation problems api return 400 httpstatus code not found 404
	 */
	@Test
	public void insertInEmail() {
		ResponseEntity response = employeeService.save("mesut konak", "mesutkon", "2018-03-23",
				Arrays.asList(new String[] { "Volleyball", "Poker", "Bowling" }));
		assertEquals(400, response.getStatusCodeValue());
	}

	@Test
	public void insertNullName() {
		ResponseEntity response = employeeService.save(null, "mesutkon@gmail.com", "2018-03-23",
				Arrays.asList(new String[] { "Volleyball", "Poker", "Bowling" }));
		assertEquals(400, response.getStatusCodeValue());
	}

	@Test
	public void getEmployeeInvalidUUID() {
		ResponseEntity response = employeeService.getEmployeeByUUID("InvalidUUID");
		assertEquals(400, response.getStatusCodeValue());
	}

	@Test
	public void getNotFoundUUID() {
		ResponseEntity response = employeeService.getEmployeeByUUID("f11209f3-2be4-4658-9be9-429bfc71a765");
		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	public void insertInvalidDate() {
		ResponseEntity response = employeeService.save("test", "mesutkon@gmail.com", "2018-03-232",
				Arrays.asList(new String[] { "Volleyball", "Poker", "Bowling" }));
		assertEquals(400, response.getStatusCodeValue());
	}

	@Test
	public void tryInsertSameEmail() {
		ResponseEntity response = employeeService.save("mesut konak", "mesutkonak@gmail.com", "2018-03-23",
				Arrays.asList(new String[] { "Volleyball", "Poker", "Bowling" }));

		response = employeeService.save("mesut konak", "mesutkonak@gmail.com", "2018-03-23",
				Arrays.asList(new String[] { "Volleyball", "Poker", "Bowling" }));
		ApiResponse apiResponse = (ApiResponse) response.getBody();
		assertEquals(400, response.getStatusCodeValue());
	}

}
