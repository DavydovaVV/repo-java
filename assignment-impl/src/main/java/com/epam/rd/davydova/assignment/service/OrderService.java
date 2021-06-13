package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface for crud-operations with database
 */
@Service
public interface OrderService {
    /**
     * Add order to database
     *
     * @param order Order object
     * @return Order object
     */
    Order add(Order order);

    /**
     * Find order by number
     *
     * @param orderNumber order number
     * @return Optional of order instance
     */
    Optional<Order> findBy(String orderNumber);

    /**
     * Find order by Id
     *
     * @param orderId order Id
     * @return Optional of order instance
     */
    Optional<Order> findBy(long orderId);

    /**
     * Find all orders
     *
     * @return Optional of List of orders
     */
    List<Order> findAll();

    /**
     * Update order
     *
     * @param order Order object
     * @return Order object
     */
    Order update(Order order);

    /**
     * Delete order from database
     *
     * @param orderId order Id
     * @return status of removal
     */
    boolean delete(long orderId);
}
