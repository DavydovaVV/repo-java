package com.epam.rd.davydova.assignment.converter.dto;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.dto.SupplierDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

/**
 * This is a class to condert entity to dto
 */
public class SupplierToDtoConverter implements Converter<Supplier, SupplierDto> {

    /**
     * Convert entity to dto
     *
     * @param supplier Supplier object
     * @return DTO of Supplier object
     */
    @Override
    public SupplierDto convert(Supplier supplier) {
        var supplierDto = new SupplierDto();

        supplierDto.setSupplierId(supplier.getSupplierId())
                .setCompanyName(supplier.getCompanyName())
                .setPhone(supplier.getPhone());

        return supplierDto;
    }

    /**
     * Get input type
     *
     * @param typeFactory type factory
     * @return java type
     */
    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Supplier.class);
    }

    /**
     * Get output type
     *
     * @param typeFactory type factory
     * @return java type
     */
    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(SupplierDto.class);
    }
}
