package com.springboot.rest.api.payroll.model;

//the only class that uses the Bean annotation in the whole application

//removed it earlier and ran it still has the same issue... the Bean annotation is NOT the problem

import com.springboot.rest.api.payroll.repository.EmployeeRepository;
import com.springboot.rest.api.payroll.repository.OrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/* Spring Boot will run ALL CommandLineRunner beans once the application context is loaded.
 * This runner will request a copy of the EmployeeRepository you just created.
 * Using it, it will create two entities and store them.*/
@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {

    return args -> {
      employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar", "Bilbo Baggins"));
      employeeRepository.save(new Employee("Frodo", "Baggins", "thief", "Frodo Baggins"));

      employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

      
      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

      orderRepository.findAll().forEach(order -> {
        log.info("Preloaded " + order);
      });
      
    };
  }
}