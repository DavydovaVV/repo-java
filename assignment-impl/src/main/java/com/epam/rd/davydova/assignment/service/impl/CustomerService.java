package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.repository.impl.CustomerRepository;
import com.epam.rd.davydova.assignment.service.interfaces.ICustomerService;
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
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Add customer to database
     *
     * @param customerName customer instance
     * @param phone        phone number
     */
    @Override
    public void add(String customerName, String phone) {
        var customerOptional = customerRepository.findBy(customerName);

        if (customerOptional.isEmpty()) {
            var customer = new Customer();
            customer.setCustomerName(customerName);
            customer.setPhone(phone);
            customerRepository.save(customer);
        } else {
            log.error("Customer with such a name is already added");
        }
    }

    /**
     * Find customer by their name
     *
     * @param customerName customer's name
     * @return customer instance
     */
    @Override
    public Optional<Customer> findBy(String customerName) {
        return customerRepository.findBy(customerName);
    }

    /**
     * Find customer by Id
     *
     * @param customerId customer Id
     * @return Optional of customer instance
     */
    @Override
    public Optional<Customer> findBy(int customerId) {
        return customerRepository.findBy(customerId);
    }

    /**
     * Find all customers
     *
     * @return Optional of List of customers
     */
    @Override
    public Optional<List> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Update customer's name and phone number
     *
     * @param customerId customer Id
     * @param phone      phone number
     */
    @Override
    public void update(int customerId, String phone) {
        var customerOptional = customerRepository.findBy(customerId);

        if (customerOptional.isPresent()) {
            var customer = customerOptional.get();
            customer.setPhone(phone);
            customerRepository.update(customer);
        } else {
            log.error("Customer is not present to be updated");
        }
    }

    /**
     * Delete customer from database
     *
     * @param customerId customer Id
     */
    @Override
    public void delete(int customerId) {
        var customerOptional = customerRepository.findBy(customerId);

        if (customerOptional.isPresent()) {
            var customer = customerOptional.get();
            customerRepository.delete(customer);
        } else {
            log.error("Customer is not present to be deleted");
        }
    }

    /**
     * Close CustomerRepository
     */
    @Override
    public void close() {
        customerRepository.close();
    }
}
