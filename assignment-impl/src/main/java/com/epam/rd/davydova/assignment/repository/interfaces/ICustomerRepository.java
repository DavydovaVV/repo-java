package com.epam.rd.davydova.assignment.repository.interfaces;

import com.epam.rd.davydova.assignment.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface for operations with database
 */
public interface ICustomerRepository {
    void save(Customer customer);

    Optional<Customer> findBy(String customerName);

    Optional<Customer> findBy(int customerId);

    Optional<List> findAll();

    void update(Customer customer);

    void delete(Customer customer);

    void close();
}
