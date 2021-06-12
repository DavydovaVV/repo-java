package com.epam.rd.davydova.assignment.repository;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is an interface for operations with database
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Find customer in database by name
     *
     * @param customerName customer name
     * @return Optional of customer
     */
    Optional<Customer> findByCustomerName(String customerName);
}
