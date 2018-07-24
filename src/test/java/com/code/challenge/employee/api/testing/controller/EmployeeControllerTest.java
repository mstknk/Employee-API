package com.code.challenge.employee.api.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.code.challenge.employee.api.EmployeeApiApplication;
import com.code.challenge.employee.api.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = EmployeeApiApplication.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
public class EmployeeControllerTest {

	@MockBean
	EmployeeServiceImpl employeeServiceImplMock;

	@Autowired
	WebApplicationContext context;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	private MockMvc mockMvc;

	@Test
	public void testCreateClientSuccessfully() throws Exception {
		this.mockMvc.perform(get("/employee-api/v1/employee/f11209f3-2be4-4658-9be9-429bfc7ss1a766"))
				.andExpect(status().isOk());
	}

}
