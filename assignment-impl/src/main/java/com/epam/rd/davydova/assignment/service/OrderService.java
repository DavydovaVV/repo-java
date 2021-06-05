package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.domain.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface OrderService {
    Optional<Order> add(int productId, int customerId, String orderNumber, int numberOfProducts);

    Optional<Order> findBy(String orderNumber);

    Optional<Order> findBy(int orderId);

    Optional<List> findAll();

    Optional<Order> update(int orderId, String orderNumber, int productId, int numberOfProducts);

    boolean delete(int orderId);

    void close();
}
