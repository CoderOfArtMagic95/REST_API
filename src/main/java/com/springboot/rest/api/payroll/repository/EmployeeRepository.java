package com.springboot.rest.api.payroll.repository;

import com.springboot.rest.api.payroll.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}