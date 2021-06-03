package com.epam.rd.davydova.assignment.service.interfaces;

import com.epam.rd.davydova.assignment.domain.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface IOrderService {
    void add(int productId, int customerId, String orderNumber, int numberOfProducts);

    Optional<Order> findBy(String orderNumber);

    Optional<Order> findBy(int orderId);

    Optional<List> findAll();

    void update(int orderId, String orderNumber, int productId, int numberOfProducts);

    void delete(int orderId);

    void close();
}
