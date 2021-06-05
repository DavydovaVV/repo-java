package com.epam.rd.davydova.assignment.converter.dto;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.dto.OrderDto;
import com.epam.rd.davydova.assignment.dto.SupplierDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import java.util.ArrayList;

/**
 * This is a class to condert entity to dto
 */
public class OrderToDtoConverter implements Converter<Order, OrderDto> {

    /**
     * Convert entity to dto
     *
     * @param order Order object
     * @return DTO of Order object
     */
    @Override
    public OrderDto convert(Order order) {
        var orderDto = new OrderDto();

        var productList = order.getProductList();
        var productIdList = new ArrayList<Long>();

        for(Product product : productList) {
            productIdList.add(product.getProductId());
        }

        orderDto.setOrderId(order.getOrderId())
                .setProductIdList(productIdList)
                .setOrderNumber(order.getOrderNumber())
                .setCustomerId(order.getCustomer().getCustomerId())
                .setTotalAmount(order.getTotalAmount());

        return orderDto;
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
