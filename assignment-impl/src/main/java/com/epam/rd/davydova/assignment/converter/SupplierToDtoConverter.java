package com.epam.rd.davydova.assignment.converter;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.dto.SupplierDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter from String to SupplierDto
 */
@Slf4j
public class SupplierToDtoConverter implements Converter<Supplier, SupplierDto> {

    /**
     * Convert supplier to supplierDto
     *
     * @param supplier Supplier object
     * @return supplierDto
     */
    @Override
    public SupplierDto convert(Supplier supplier) {
        var supplierDto = new SupplierDto()
                .setCompanyName(supplier.getCompanyName())
                .setPhone(supplier.getPhone());
        log.info("convert() - convert from '{}' to {}", supplier, supplierDto);
        return supplierDto;
    }
}
