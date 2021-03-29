package com.springboot.rest.api.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.api.payroll.model.Order;

public
interface OrderRepository extends JpaRepository<Order, Long> {
}
