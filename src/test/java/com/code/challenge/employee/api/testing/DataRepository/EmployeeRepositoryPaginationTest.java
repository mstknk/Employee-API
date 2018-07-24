package com.code.challenge.employee.api.testing.DataRepository;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.code.challenge.employee.api.EmployeeApiApplication;
import com.code.challenge.employee.api.dao.EmployeeRepository;
import com.code.challenge.employee.api.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeApiApplication.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
@ActiveProfiles("dev")
public class EmployeeRepositoryPaginationTest extends AbstractEmployeeDataRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void employeeRepositoryPaginationTest() {
		long count = employeeRepository.count();
		Page<Employee> emloyeesPage = employeeRepository.findAll(PageRequest.of(0, 100));
		Assert.assertEquals(count, emloyeesPage.getTotalElements());
		Assert.assertEquals(0, emloyeesPage.getNumber());
		Assert.assertEquals(100, emloyeesPage.getNumberOfElements());
		Assert.assertEquals(100, emloyeesPage.getContent().size());
		/** next page */
		emloyeesPage = employeeRepository.findAll(PageRequest.of(1, 100));
		Assert.assertEquals(1, emloyeesPage.getNumber());

	}

}
