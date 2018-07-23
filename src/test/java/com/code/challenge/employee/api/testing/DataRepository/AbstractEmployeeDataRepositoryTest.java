package com.code.challenge.employee.api.testing.DataRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import com.code.challenge.employee.api.dao.EmployeeRepository;
import com.code.challenge.employee.api.model.Employee;
import com.code.challenge.employee.api.model.Hobby;

public abstract class AbstractEmployeeDataRepositoryTest {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Before
	public void insertEmployees() {
		for (int i = 0; i < 110; i++) {
			Employee employee = new Employee("mesut konak", "mesutkonak_" + i + "@gmail.com",
					LocalDate.parse("2018-03-23"));
			Set<Hobby> hobbies = new HashSet<>();

			hobbies.add(new Hobby("Soccer", employee));
			hobbies.add(new Hobby("Music", employee));
			employee.setHobbies(hobbies);
			employeeRepository.save(employee);

		}
	}
}
