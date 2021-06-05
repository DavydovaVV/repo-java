package com.epam.rd.davydova.assignment.converter.entity;

import com.epam.rd.davydova.assignment.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter from String to CustomerDto
 */
@Slf4j
public class StringToCustomerConverter implements Converter<String, CustomerDto> {

    /**
     * Convert string to customerDto
     *
     * @param string received string with data
     * @return customerDto
     */
    @Override
    public CustomerDto convert(String string) {
        String[] strings = string.split(",");
        var customerDto = new CustomerDto()
                .setCustomerName(strings[0])
                .setPhone(strings[1]);
        log.info("convert() - convert from '{}' to {}", string, customerDto);
        return customerDto;
    }
}
