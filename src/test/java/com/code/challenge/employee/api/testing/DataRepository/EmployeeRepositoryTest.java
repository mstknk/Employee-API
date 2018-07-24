package com.code.challenge.employee.api.testing.DataRepository;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
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
	private static UUID testUUID;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testAInsertEmployee() {
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
	public void testBCreateAnEmpoyeeWithSameEmailExceptionShouldBeThrown() {
		Employee employee = new Employee("mesut konak", "mesutkonak@gmail.com", LocalDate.parse("2018-03-23"));
		Exception expectedException = null;
		try {
			employeeRepository.save(employee);
			fail("Expected exception");
		} catch (Exception e) {
			expectedException = e;
		}

		assertTrue(expectedException instanceof DataIntegrityViolationException);

	}

	@Test
	public void testCFindEmpoyeeByUUIDTest() {
		Employee employeeDB = employeeRepository.getEmployeeByUUID(testUUID);
		Set<Hobby> hobbies = employeeDB.getHobbies();
		Assert.assertTrue(employeeDB.getEmail().contentEquals("mesutkonak@gmail.com"));
		Assert.assertTrue(employeeDB.getFullName().contentEquals("mesut konak"));
		Assert.assertTrue(testUUID.toString().contentEquals(employeeDB.getUuid().toString()));
		Assert.assertEquals(2, hobbies.size());
	}

	@Test
	public void testDUpdatempoyeeByUUIDTest() {
		String newEmail = "mesutkonak89@gmail.com";
		String newFullName = "mesut sunay sali";
		Employee employeeDB = employeeRepository.getEmployeeByUUID(testUUID);
		employeeDB.setEmail(newEmail);
		employeeDB.setFullName(newFullName);
		employeeDB = employeeRepository.save(employeeDB);
		Assert.assertTrue(employeeDB.getEmail().contentEquals(newEmail));
		Assert.assertTrue(employeeDB.getFullName().contentEquals(newFullName));
		Assert.assertTrue(testUUID.toString().contentEquals(employeeDB.getUuid().toString()));
	}

	@Test
	public void testEDeleteEmployeeByUUIDTest() {
		Employee employeeDB = employeeRepository.getEmployeeByUUID(testUUID);
		employeeRepository.delete(employeeDB);
		employeeDB = employeeRepository.getEmployeeByUUID(testUUID);
		Assert.assertNull(employeeDB);
	}
}
