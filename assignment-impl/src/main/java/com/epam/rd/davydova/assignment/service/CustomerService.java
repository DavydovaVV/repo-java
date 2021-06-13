package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface for crud-operations with database
 */
@Service
public interface CustomerService {
    /**
     * Add customer to database
     *
     * @param customer Customer object
     * @return Optional of Customer object
     */
    Customer add(Customer customer);

    /**
     * Find customer by their name
     *
     * @param customerName customer's name
     * @return customer instance
     */
    Optional<Customer> findBy(String customerName);

    /**
     * Find customer by Id
     *
     * @param customerId customer Id
     * @return Optional of customer instance
     */
    Optional<Customer> findBy(long customerId);

    /**
     * Find all customers
     *
     * @return Optional of List of customers
     */
    List<Customer> findAll();

    /**
     * Update customer
     *
     * @param customer Customer object
     * @return Customer object
     */
    Customer update(Customer customer);

    /**
     * Delete customer from database
     *
     * @param customerId customer Id
     */
    boolean delete(long customerId);
}
