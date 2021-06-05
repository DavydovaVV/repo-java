package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.dto.OrderDto;
import com.epam.rd.davydova.assignment.domain.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface OrderService {
    Order add(OrderDto orderDto);

    Optional<Order> findBy(String orderNumber);

    Optional<Order> findBy(long orderId);

    List<Order> findAll();

    Order update(OrderDto orderDto);

    boolean delete(long orderId);
}
