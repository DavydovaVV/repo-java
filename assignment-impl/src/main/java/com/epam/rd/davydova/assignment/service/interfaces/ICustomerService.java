package com.epam.rd.davydova.assignment.service.interfaces;

import com.epam.rd.davydova.assignment.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface ICustomerService {
    void add(String customerName, String phone);

    Optional<Customer> findBy(String customerName);

    Optional<Customer> findBy(int customerId);

    Optional<List> findAll();

    void update(int customerId, String phone);

    void delete(int customerId);

    void close();
}
