package com.epam.rd.davydova.assignment.converter.entity;

import com.epam.rd.davydova.assignment.dto.SupplierDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter from String to SupplierDto
 */
@Slf4j
public class StringToSupplierConverter implements Converter<String, SupplierDto> {

    /**
     * Convert string to supplierDto
     *
     * @param string received string with data
     * @return supplierDto
     */
    @Override
    public SupplierDto convert(String string) {
        String[] strings = string.split(",");
        var supplierDto = new SupplierDto()
                .setCompanyName(strings[0])
                .setPhone(strings[1]);
        log.info("convert() - convert from '{}' to {}", string, supplierDto);
        return supplierDto;
    }
}
