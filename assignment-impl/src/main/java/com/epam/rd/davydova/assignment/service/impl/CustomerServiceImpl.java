package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;

    /**
     * Add customer to database
     *
     * @param customer Customer object
     * @return Optional of Customer object
     */
    @Override
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Find customer by their name
     *
     * @param customerName customer's name
     * @return customer instance
     */
    @Override
    public Optional<Customer> findBy(String customerName) {
        return customerRepository.findByCustomerName(customerName);
    }

    /**
     * Find customer by Id
     *
     * @param customerId customer Id
     * @return Optional of customer instance
     */
    @Override
    public Optional<Customer> findBy(long customerId) {
        return customerRepository.findById(customerId);
    }

    /**
     * Find all customers
     *
     * @return Optional of List of customers
     */
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Update customer
     *
     * @param customer Customer object
     * @return
     */
    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Delete customer from database
     *
     * @param customerId customer Id
     */
    @Override
    public boolean delete(long customerId) {
        var customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            var customer = customerOptional.get();
            customerRepository.delete(customer);
            if (!customerRepository.existsById(customerId)) {
                return true;
            }
        } else {
            log.error("Customer Id is not found. Customer is not deleted");
        }
        return false;
    }
}
