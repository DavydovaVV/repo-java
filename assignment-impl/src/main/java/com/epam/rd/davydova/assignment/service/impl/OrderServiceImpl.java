package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.repository.impl.CustomerRepositoryImpl;
import com.epam.rd.davydova.assignment.repository.impl.OrderRepositoryImpl;
import com.epam.rd.davydova.assignment.repository.impl.ProductRepositoryImpl;
import com.epam.rd.davydova.assignment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * This is a class for operations with Order entity and database
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepositoryImpl orderRepositoryImpl;
    private final CustomerRepositoryImpl customerRepositoryImpl;
    private final ProductRepositoryImpl productRepositoryImpl;

    /**
     * Add order to database
     *
     * @param productId        product Id
     * @param customerId       customer Id
     * @param orderNumber      order number
     * @param numberOfProducts number of products
     */
    @Override
    public Optional<Order> add(int productId, int customerId, String orderNumber, int numberOfProducts) {
        var orderOptional = orderRepositoryImpl.findBy(orderNumber);
        var customerOptional = customerRepositoryImpl.findBy(customerId);
        var productOptional = productRepositoryImpl.findBy(productId);

        if (orderOptional.isEmpty()) {
            if (customerOptional.isPresent()) {
                if (productOptional.isPresent()) {
                    var order = new Order();
                    var customer = customerOptional.get();
                    var product = productOptional.get();
                    var pricePerUnit = product.getUnitPrice().doubleValue();

                    order.setOrderDate(new Date());
                    order.setCustomer(customer);
                    order.setOrderNumber(orderNumber);
                    order.setTotalAmount(BigDecimal.valueOf(pricePerUnit * numberOfProducts));
                    order.addToList(product);
                    product.addToList(order);
                    orderRepositoryImpl.save(order);
                    return orderRepositoryImpl.findBy(order.getOrderId());
                } else {
                    log.error("Product is not added. Order cannot be added");
                }
            } else {
                log.error("Customer is not added. Order cannot be added");
            }
        } else {
            log.error("Order with such a number is already added");
        }
        return Optional.empty();
    }

    /**
     * Find order by number
     *
     * @param orderNumber order number
     * @return Optional of order instance
     */
    @Override
    public Optional<Order> findBy(String orderNumber) {
        return orderRepositoryImpl.findBy(orderNumber);
    }

    /**
     * Find order by Id
     *
     * @param orderId order Id
     * @return Optional of order instance
     */
    @Override
    public Optional<Order> findBy(int orderId) {
        return orderRepositoryImpl.findBy(orderId);
    }

    /**
     * Find all orders
     *
     * @return Optional of List of orders
     */
    @Override
    public Optional<List> findAll() {
        return orderRepositoryImpl.findAll();
    }

    /**
     * Update order
     *
     * @param orderId          order Id
     * @param orderNumber      order Number
     * @param productId        product Id
     * @param numberOfProducts quantity of product unit in order
     * @return
     */
    @Override
    public Optional<Order> update(int orderId, String orderNumber, int productId, int numberOfProducts) {
        var orderOptional = orderRepositoryImpl.findBy(orderId);
        var productOptional = productRepositoryImpl.findBy(productId);

        if (orderOptional.isPresent()) {
            if (productOptional.isPresent()) {
                var order = orderOptional.get();
                var product = productOptional.get();
                var pricePerUnit = product.getUnitPrice().doubleValue();
                var totalAmount = order.getTotalAmount();

                order.setOrderDate(new Date());
                order.setOrderNumber(orderNumber);
                order.setTotalAmount(BigDecimal.valueOf(pricePerUnit * numberOfProducts)
                        .add(totalAmount));

                if (!order.getProductList().contains(product)) {
                    order.addToList(product);
                    product.addToList(order);
                }

                orderRepositoryImpl.update(order);
                return orderRepositoryImpl.findBy(orderId);
            } else {
                log.error("Product is not present to be added. Order cannot be updated");
            }
        } else {
            log.error("Order is not present to be updated");
        }
        return Optional.empty();
    }

    /**
     * Delete order from database
     *
     * @param orderId order Id
     * @return status of removal
     */
    @Override
    public boolean delete(int orderId) {
        var orderOptional = orderRepositoryImpl.findBy(orderId);

        if (orderOptional.isPresent()) {
            var order = orderOptional.get();
            orderRepositoryImpl.delete(order);
            return findBy(order.getOrderId()).isEmpty();
        } else {
            log.error("Order is not present to be deleted");
        }
        return false;
    }

    /**
     * Close OrderRepository
     */
    @Override
    public void close() {
        orderRepositoryImpl.close();
    }
}
