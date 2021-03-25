package com.springboot.rest.api.payroll.controller;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}
