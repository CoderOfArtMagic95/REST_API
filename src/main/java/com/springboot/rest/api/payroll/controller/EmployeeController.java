package com.springboot.rest.api.payroll.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.springboot.rest.api.payroll.model.Employee;
import com.springboot.rest.api.payroll.repository.EmployeeRepository;
import com.springboot.rest.api.payroll.model.EmployeeModelAssembler;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/* @RestController indicates that the data returned by each method 
 * will be written straight into the response body instead of rendering a template.
 * 
 * An EmployeeRepository is injected by constructor into the controller.
 * We have routes for each operation (@GetMapping, @PostMapping, @PutMapping
 * and @DeleteMapping, corresponding to HTTP GET, POST, PUT, and DELETE calls).
 * (NOTE: It’s useful to read each method and understand what they do.)
 * 
 * EmployeeNotFoundException is an exception used to indicate when an employee
 *  is looked up but not found.*/

@RestController
public
class EmployeeController {

  private final EmployeeRepository repository;
  
  private final EmployeeModelAssembler assembler;

  //Injecting EmployeeModelAssembler into the controller class' constructor
    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {

      this.repository = repository;
      this.assembler = assembler;
    }
  
  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }
  
  // Single item
  //Getting single item resource using the assembler
  @GetMapping("/employees/{id}")
public
  EntityManager one(@PathVariable Long id) {

    Employee employee = repository.findById(id) //
        .orElseThrow(() -> new EmployeeNotFoundException(id));
//for RESTful API
    /* EntityModel.of(employee, //
        linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
        linkTo(methodOn(EmployeeController.class).all()).withRel("employees")); */

      return (EntityManager) assembler.toModel(employee);
  }
  // Single item
  @GetMapping("/employees/{id}")
  public
  EntityModel<Employee> one(@PathVariable Long id, EmployeeRepository repository) {

    Employee employee = repository.findById(id) //
        .orElseThrow(() -> new EmployeeNotFoundException(id));

    return EntityModel.of(employee, //
        linkTo(methodOn(EmployeeController.class).one(id, repository)).withSelfRel(),
        linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
  }
  
//Getting an aggrgate root resources
/*  
  @GetMapping("/employees")
public
  CollectionModel<EntityModel<Employee>> all() {

    List<EntityModel<Employee>> employees = repository.findAll().stream()
        .map(employee -> EntityModel.of(employee,
            linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
            linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
        .collect(Collectors.toList());

    return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }
*/  
//Aggregate root //Getting an aggregate root
 // tag::get-aggregate-root[]
  
 //Getting aggregate root resource using the assembler
  @GetMapping("/employees")
public
  CollectionModel<EntityModel<Employee>> all() {

    List<EntityModel<Employee>> employees = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }
  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }
  
  
 @GetMapping("/employees")
 List<Employee> all(EmployeeRepository repository) {
   return repository.findAll();
 }
 // end::get-aggregate-root[]

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}