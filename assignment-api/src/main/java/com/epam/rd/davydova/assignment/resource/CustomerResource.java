package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is an interface to interact with database from api layer
 */
@RequestMapping(value = "/customer")
public interface CustomerResource {

    /**
     * Add customer to database
     *
     * @param customerDto DTO of Customer object
     * @return string result of method
     */
    @PostMapping
    CustomerDto addCustomer(@RequestBody CustomerDto customerDto);

    /**
     * Get customer from database
     *
     * @param id customer Id
     * @return string result of method
     */
    @GetMapping(value = "/{id}")
    @ResponseBody List<CustomerDto> getCustomer(@PathVariable(value = "id", required = false) Long id);

    /**
     * Update customer in database
     *
     * @param customerDto DTO of Customer object
     * @return string result of method
     */
    @PutMapping
    @ResponseBody CustomerDto updateCustomer(@RequestBody CustomerDto customerDto);

    /**
     * Delete customer from database
     *
     * @param id customer Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/{id}")
    HttpStatus deleteCustomer(@PathVariable(value = "id") Long id);
}
