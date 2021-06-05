package com.epam.rd.davydova.assignment.repository;

import com.epam.rd.davydova.assignment.domain.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface for operations with database
 */
public interface OrderRepository {
    void save(Order order);

    Optional<Order> findBy(String orderNumber);

    Optional<Order> findBy(int orderId);

    Optional<List> findAll();

    void update(Order order);

    void delete(Order order);

    void close();
}
