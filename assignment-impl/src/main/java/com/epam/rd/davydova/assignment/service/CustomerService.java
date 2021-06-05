package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.dto.CustomerDto;
import com.epam.rd.davydova.assignment.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface CustomerService {
    Customer add(CustomerDto customerDto);

    Optional<Customer> findBy(String customerName);

    Optional<Customer> findBy(long customerId);

    List<Customer> findAll();

    Customer update(CustomerDto customerDto);

    boolean delete(long customerId);
}
