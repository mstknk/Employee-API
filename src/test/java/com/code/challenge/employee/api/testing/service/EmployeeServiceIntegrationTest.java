package com.code.challenge.employee.api.testing.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.code.challenge.employee.api.EmployeeApiApplication;
import com.code.challenge.employee.api.model.Employee;
import com.code.challenge.employee.api.responses.ApiResponse;
import com.code.challenge.employee.api.responses.ApiResult;
import com.code.challenge.employee.api.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeApiApplication.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeServiceIntegrationTest {

	private static Employee testEmployee;
	@Autowired
	private EmployeeService employeeService;

	@Test
	public void testAInsertEmployee() {
		ResponseEntity response = employeeService.save("mesut konak", "mesutkonak12@gmail.com", "2018-03-23",
				Arrays.asList(new String[] { "Volleyball", "Poker", "Bowling" }));
		assertEquals(201, response.getStatusCodeValue());

	}

	@Test
	public void testBgetAllEmployee() {
		ResponseEntity responseEntity = employeeService.getEmployees(0, 1);
		ApiResult apiResult = (ApiResult) ((ApiResponse) responseEntity.getBody()).getResponse();
		assertEquals(200, responseEntity.getStatusCodeValue());
		testEmployee = apiResult.getEmployees().get(0);
		Assert.assertNotNull(testEmployee);

	}

	@Test
	public void testCgetEmployee() {
		ResponseEntity responseEntity = employeeService.getEmployeeByUUID(testEmployee.getUuid().toString());
		Employee employee = (Employee) ((ApiResponse) responseEntity.getBody()).getResponse();
		assertEquals(302, responseEntity.getStatusCodeValue());
		Assert.assertNotNull(employee);

	}

	@Test
	public void testDUpdateEmployee() {
		ResponseEntity responseEntity = employeeService.update(testEmployee.getUuid().toString(), "new Name",
				testEmployee.getEmail(), testEmployee.getBirthday().toString());
		assertEquals(200, responseEntity.getStatusCodeValue());
		responseEntity = employeeService.getEmployeeByUUID(testEmployee.getUuid().toString());
		Employee employee = (Employee) ((ApiResponse) responseEntity.getBody()).getResponse();
		Assert.assertNotNull(employee);
		Assert.assertEquals("new Name", employee.getFullName());

	}

	@Test
	public void testEDeleteEmployee() {
		ResponseEntity responseEntity = employeeService.deleteByUUID(testEmployee.getUuid().toString());
		assertEquals(200, responseEntity.getStatusCodeValue());
		responseEntity = employeeService.getEmployeeByUUID(testEmployee.getUuid().toString());
		assertEquals(404, responseEntity.getStatusCodeValue());

	}

}
