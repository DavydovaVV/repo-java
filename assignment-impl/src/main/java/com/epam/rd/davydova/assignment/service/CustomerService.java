package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface CustomerService {
    Optional<Customer> add(String customerName, String phone);

    Optional<Customer> findBy(String customerName);

    Optional<Customer> findBy(int customerId);

    Optional<List> findAll();

    Optional<Customer> update(int customerId, String phone);

    boolean delete(int customerId);

    void close();
}
