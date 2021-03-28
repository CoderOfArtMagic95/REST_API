package com.springboot.rest.api.payroll.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/* @Entity is a JPA annotation to make this object
 * ready for storage in a JPA-based data store.
 * 
 * id, name, and role are attributes of our 
 * Employee domain object. id is marked with more
 * JPA annotations to indicate it’s the primary key
 * and automatically populated by the JPA provider.
 * 
 * a custom constructor is created when we need 
 * to create a new instance, but don’t yet have an id.*/

  @Entity
  public
  class Employee {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String fullName;

    Employee() {}

    Employee(String firstName, String lastName, String role, String fullName) {

      this.firstName = firstName;
      this.lastName = lastName;
      this.fullName = fullName;
      this.role = role;
    }

    public String getName() {
      return this.firstName + " " + this.lastName + " " + this.fullName;
    }

    public void setName(String name) {
      String[] parts = name.split(" ");
      this.firstName = parts[0];
      this.lastName = parts[1];
      this.fullName = parts[2];
    }

    public Long getId() {
      return this.id;
    }

    public String getFirstName() {
      return this.firstName;
    }

    public String getLastName() {
      return this.lastName;
    }

    public String getFullName() {
        return this.fullName;
      }
    
    public String getRole() {
      return this.role;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public void setFullName() {
    	this.fullName = firstName + " " + lastName;
      }
    
    public void setRole(String role) {
      this.role = role;
    }

    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof Employee))
        return false;
      Employee employee = (Employee) o;
      return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
          && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.id, this.firstName, this.lastName, this.role);
    }

    @Override
    public String toString() {
      return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
          + '\'' + ", role='" + this.role + '\'' + '}';
    }
}
