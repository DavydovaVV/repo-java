package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.repository.OrderRepository;
import com.epam.rd.davydova.assignment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * This is a class for operations with Order entity and database
 */
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    /**
     * Add order to database
     *
     * @param order Order object
     * @return Optional of Order object
     */
    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Find order by number
     *
     * @param orderNumber order number
     * @return Optional of order instance
     */
    @Override
    public Optional<Order> findBy(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    /**
     * Find order by Id
     *
     * @param orderId order Id
     * @return Optional of order instance
     */
    @Override
    public Optional<Order> findBy(long orderId) {
        return orderRepository.findById(orderId);
    }

    /**
     * Find all orders
     *
     * @return Optional of List of orders
     */
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * Update order
     *
     * @param order Order object
     * @return Optional of Order object
     */
    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Delete order from database
     *
     * @param orderId order Id
     * @return status of removal
     */
    @Override
    public boolean delete(long orderId) {
        var orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            var order = orderOptional.get();
            orderRepository.delete(order);
            return !orderRepository.existsById(orderId);
        } else {
            log.error("Order Id is not found. Order is not deleted");
        }
        return false;
    }
}
