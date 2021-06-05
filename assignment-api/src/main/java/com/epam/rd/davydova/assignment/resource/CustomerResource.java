package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is an interface to interact with database from api layer
 */
@RequestMapping("/customer")
public interface CustomerResource {

    /**
     * Add customer to database
     *
     * @param stringCustomer string representation of Customer object
     * @return string result of method
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    CustomerDto addCustomer(@RequestBody String stringCustomer);

    /**
     * Get customer from database
     *
     * @param id customer Id
     * @return string result of method
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    List<CustomerDto> getCustomer(@PathVariable(required = false) Long id);

    /**
     * Update customer in database
     *
     * @param stringCustomer string representation of Customer object
     * @return string result of method
     */
    @PutMapping
    CustomerDto updateCustomer(@RequestBody String stringCustomer);

    /**
     * Delete customer from database
     *
     * @param id customer Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/{id}")
    HttpStatus deleteCustomer(@PathVariable long id);
}
