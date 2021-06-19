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
    @ResponseBody
    @PostMapping(value = "/add")
    CustomerDto addCustomer(@RequestBody CustomerDto customerDto);

    /**
     * Get customer from database
     *
     * @param id customer Id
     * @return string result of method
     */
    @ResponseBody
    @GetMapping(value = "/get")
    List<CustomerDto> getCustomer(@RequestParam(value = "id", required = false) Long id);

    /**
     * Update customer in database
     *
     * @param customerDto DTO of Customer object
     * @return string result of method
     */
    @ResponseBody
    @PutMapping(value = "/update")
    CustomerDto updateCustomer(@RequestBody CustomerDto customerDto);

    /**
     * Delete customer from database
     *
     * @param id customer Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/delete")
    HttpStatus deleteCustomer(@RequestParam(value = "id") Long id);
}
