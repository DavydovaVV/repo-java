package com.epam.rd.davydova.assignment.converter.dto;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.dto.ProductDto;
import com.epam.rd.davydova.assignment.dto.SupplierDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import java.util.ArrayList;

/**
 * This is a class to condert entity to dto
 */
public class ProductToDtoConverter implements Converter<Product, ProductDto> {

    /**
     * Convert entity to dto
     *
     * @param product Product object
     * @return DTO of Product object
     */
    @Override
    public ProductDto convert(Product product) {
        var productDto = new ProductDto();

        var orderList = product.getOrderList();
        var orderIdList = new ArrayList<Long>();

        for(Order order : orderList) {
            orderIdList.add(order.getOrderId());
        }

        productDto.setProductId(product.getProductId())
                .setProductName(product.getProductName())
                .setSupplierId(product.getSupplier().getSupplierId())
                .setUnitPrice(product.getUnitPrice())
                .setOrderIdList(orderIdList)
                .setDiscontinued(product.isDiscontinued());

        return productDto;
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
