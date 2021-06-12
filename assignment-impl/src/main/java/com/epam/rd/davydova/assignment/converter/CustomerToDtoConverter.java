package com.epam.rd.davydova.assignment.converter;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter from Customer to CustomerDto
 */
@Slf4j
public class CustomerToDtoConverter implements Converter<Customer, CustomerDto> {

    /**
     * Convert Customer to CustomerDto
     *
     * @param customer Customer object
     * @return Customer object
     */
    @Override
    public CustomerDto convert(Customer customer) {
        var customerDto = new CustomerDto()
                .setCustomerName(customer.getCustomerName())
                .setPhone(customer.getPhone());
        log.info("convert() - convert from '{}' to {}", customer, customerDto);
        return customerDto;
    }
}
