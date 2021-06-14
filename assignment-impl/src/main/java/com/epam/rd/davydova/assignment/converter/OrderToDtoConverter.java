package com.epam.rd.davydova.assignment.converter;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter from Order to OrderDto
 */
@Slf4j
public class OrderToDtoConverter implements Converter<Order, OrderDto> {

    /**
     * Convert Order to OrderDto
     *
     * @param order Order object
     * @return OrderDto object
     */
    @Override
    public OrderDto convert(Order order) {
        var productList = order.getProductList();
        List<Long> productIdList = new ArrayList<>();

        for(Product product : productList) {
            productIdList.add(product.getProductId());
        }
        var orderDto = new OrderDto()
                .setOrderId(order.getOrderId())
                .setOrderNumber(order.getOrderNumber())
                .setProductIdList(productIdList)
                .setCustomerId(order.getCustomer().getCustomerId())
                .setOrderDate(order.getOrderDate())
                .setTotalAmount(order.getTotalAmount());
        log.info("convert() - convert from '{}' to {}", order, orderDto);
        return orderDto;
    }
}
