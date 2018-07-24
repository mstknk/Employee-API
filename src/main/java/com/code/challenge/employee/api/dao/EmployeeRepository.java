package com.code.challenge.employee.api.dao;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.code.challenge.employee.api.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	@Query("select e from Employee e " + " where " + " e.uuid = :uuid  ")
	public Employee getEmployeeByUUID(@Param("uuid") UUID uuid);

	@Query("select e from Employee e " + " where " + " e.email = :email  ")
	public Employee getEmployeeByEmail(@Param("email") String email);

	Page<Employee> findAll(Pageable pageRequest);
}
