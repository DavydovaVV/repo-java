package com.epam.rd.davydova.assignment.converter.dto;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.dto.CustomerDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

/**
 * This is a class to condert entity to dto
 */
public class CustomerToDtoConverter implements Converter<Customer, CustomerDto> {

    /**
     * Convert entity to dto
     *
     * @param customer Customer object
     * @return DTO of Customer object
     */
    @Override
    public CustomerDto convert(Customer customer) {
        var customerDto = new CustomerDto();

        customerDto.setCustomerId(customer.getCustomerId())
                .setCustomerName(customer.getCustomerName())
                .setPhone(customer.getPhone());

        return customerDto;
    }

    /**
     * Get input type
     *
     * @param typeFactory type factory
     * @return java type
     */
    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Customer.class);
    }

    /**
     * Get output type
     *
     * @param typeFactory type factory
     * @return java type
     */
    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(CustomerDto.class);
    }
}
