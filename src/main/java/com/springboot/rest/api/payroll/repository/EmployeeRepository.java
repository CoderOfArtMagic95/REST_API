package com.springboot.rest.api.payroll.repository;

//might need to remake later and see where it has gone wrong

import com.springboot.rest.api.payroll.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}