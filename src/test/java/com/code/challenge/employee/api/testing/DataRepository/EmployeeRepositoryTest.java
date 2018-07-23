package com.code.challenge.employee.api.EmployeeAPI;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.code.challenge.employee.api.EmployeeApiApplication;
import com.code.challenge.employee.api.dao.EmployeeRepository;
import com.code.challenge.employee.api.model.Employee;
import com.code.challenge.employee.api.model.Hobby;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeApiApplication.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeRepositoryTest {
	private UUID testUUID;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Before
	public void insertEmployeeTest() {
		Employee employee = new Employee("mesut konak", "mesutkonak@gmail.com", LocalDate.parse("2018-03-23"));
		Set<Hobby> hobbies = new HashSet<>();

		hobbies.add(new Hobby("Soccer", employee));
		hobbies.add(new Hobby("Music", employee));
		employee.setHobbies(hobbies);
		Employee employeeDB = employeeRepository.save(employee);
		Assert.assertNotNull(employeeDB);
		testUUID = employeeDB.getUuid();
	}

	@Test
	@Ignore
	public void createAnEmpoyeeWithSameEmail() {
		Employee employee = new Employee("mesut konak", "mesutkonak@gmail.com", LocalDate.parse("2018-03-23"));
		try {
			Employee employeeDB = employeeRepository.save(employee);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void findEmpoyeeByUUIDTest() {
		Employee employeeDB = employeeRepository.getEmployeeByUUID(testUUID);
		Set<Hobby> hobbies = employeeDB.getHobbies();
		Assert.assertTrue(employeeDB.getEmail().contentEquals("mesutkonak@gmail.com"));
		Assert.assertTrue(employeeDB.getFullName().contentEquals("mesut konak"));
		Assert.assertTrue(testUUID.toString().contentEquals(employeeDB.getUuid().toString()));
		Assert.assertEquals(2, hobbies.size());
	}

	@Test
	@Ignore
	public void deleteEmployeeByUUIDTest() {
		Employee employeeDB = employeeRepository.getEmployeeByUUID(testUUID);
		employeeRepository.delete(employeeDB);
		employeeDB = employeeRepository.getEmployeeByUUID(testUUID);
		Assert.assertNull(employeeDB);
	}
}
