package com.springboot.rest.api.payroll.controller;

@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException {

  public OrderNotFoundException(Long  id) {
    super("Could not find order " + id);
  }
}
