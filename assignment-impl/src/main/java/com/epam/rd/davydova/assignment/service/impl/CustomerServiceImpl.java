package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.repository.impl.CustomerRepositoryImpl;
import com.epam.rd.davydova.assignment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a class to wrap crud-operations of CustomerRepository
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepositoryImpl customerRepositoryImpl;

    /**
     * Add customer to database
     *
     * @param customerName customer name
     * @param phone        phone number
     * @return Optional of Customer object
     */
    @Override
    public Optional<Customer> add(String customerName, String phone) {
        var customerOptional = customerRepositoryImpl.findBy(customerName);

        if (customerOptional.isEmpty()) {
            var customer = new Customer();
            customer.setCustomerName(customerName);
            customer.setPhone(phone);
            customerRepositoryImpl.save(customer);
            return customerRepositoryImpl.findBy(customerName);
        } else {
            log.error("Customer with such a name is already added");
        }
        return Optional.empty();
    }

    /**
     * Find customer by their name
     *
     * @param customerName customer's name
     * @return customer instance
     */
    @Override
    public Optional<Customer> findBy(String customerName) {
        return customerRepositoryImpl.findBy(customerName);
    }

    /**
     * Find customer by Id
     *
     * @param customerId customer Id
     * @return Optional of customer instance
     */
    @Override
    public Optional<Customer> findBy(int customerId) {
        return customerRepositoryImpl.findBy(customerId);
    }

    /**
     * Find all customers
     *
     * @return Optional of List of customers
     */
    @Override
    public Optional<List> findAll() {
        return customerRepositoryImpl.findAll();
    }

    /**
     * Update customer's name and phone number
     *
     * @param customerId customer Id
     * @param phone      phone number
     */
    @Override
    public Optional<Customer> update(int customerId, String phone) {
        var customerOptional = customerRepositoryImpl.findBy(customerId);

        if (customerOptional.isPresent()) {
            var customer = customerOptional.get();
            customer.setPhone(phone);
            customerRepositoryImpl.update(customer);
            return customerRepositoryImpl.findBy(customerId);
        } else {
            log.error("Customer is not present to be updated");
        }
        return Optional.empty();
    }

    /**
     * Delete customer from database
     *
     * @param customerId customer Id
     */
    @Override
    public boolean delete(int customerId) {
        var customerOptional = customerRepositoryImpl.findBy(customerId);

        if (customerOptional.isPresent()) {
            var customer = customerOptional.get();
            customerRepositoryImpl.delete(customer);
            return customerRepositoryImpl.findBy(customerId).isEmpty();
        } else {
            log.error("Customer is not present to be deleted");
        }
        return false;
    }

    /**
     * Close CustomerRepository
     */
    @Override
    public void close() {
        customerRepositoryImpl.close();
    }
}
