package com.code.challenge.employee.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.code.challenge.employee.api.service.EmployeeService;
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
	private EmployeeService employeeService;

	@ApiOperation(value = "Create an employee")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Employee was created successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/v1/employee", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity createEmployee(
			@ApiParam(value = "E­mail address", required = true) @RequestParam("email") String email,
			@ApiParam(value = "Full name (first and last name)", required = true) @RequestParam("fullName") String fullName,
			@ApiParam(value = "Birthday  YYYY­-MM­-DD)", required = false) @RequestParam("birthday") String birthday,
			@ApiParam(value = "List of hobbies (soccer, music, etc)", required = false) @RequestParam("hobbies") List<String> hobbies) {

		return employeeService.save(fullName, email, birthday, hobbies);
	}

	@ApiOperation(value = "View a list of all employee ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/v1/employees", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getAllEmployees(
			@ApiParam(value = "How many employees to return at once; 100 maximum.", defaultValue = "100", allowableValues = "range[1, 1000]") @RequestParam("pageSize") int pageSize,
			@ApiParam(value = "The page of the result set.", defaultValue = "0", allowableValues = "range[0, 1000]") @RequestParam("pageNumber") int pageNumber,
			Model model) {
		return employeeService.getEmployees(pageNumber, pageSize);
	}

	@ApiOperation(value = "Find an employee by uuid")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Employee uuid"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Employee not found") })
	@RequestMapping(value = "/v1/employee/{uuid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getEmployeeByUUID(
			@ApiParam(value = "Employee uuid", required = true) @PathVariable(value = "uuid") String uuid) {
		return employeeService.getEmployeeByUUID(uuid);
	}

	@ApiOperation(value = "Update an employee by uuid")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Employee uuid"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Employee not found") })
	@RequestMapping(value = "/v1/employees/{uuid}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity updateEmployee(
			@ApiParam(value = "Employee uuid to Update", required = true) @PathVariable(value = "uuid") String uuid,
			@ApiParam(value = "E­mail address", required = true) @RequestParam("email") String email,
			@ApiParam(value = "Full name (first and last name)", required = true) @RequestParam("fullName") String fullName,
			@ApiParam(value = "Birthday  YYYY­-MM­-DD)", required = false) @RequestParam("birthday") String birthday) {

		return employeeService.update(uuid,fullName, email, birthday);
	}

	@ApiOperation(value = "Delete an employee by uuid")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Employee uuid"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Employee not found") })
	@RequestMapping(value = "/v1/employee/{uuid}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity delete(
			@ApiParam(value = "Employee uuid to delete", required = true) @PathVariable(value = "uuid") String uuid) {
		return employeeService.deleteByUUID(uuid);
	}
}
