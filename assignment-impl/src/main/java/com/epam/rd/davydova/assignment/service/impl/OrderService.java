package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.repository.impl.CustomerRepository;
import com.epam.rd.davydova.assignment.repository.impl.OrderRepository;
import com.epam.rd.davydova.assignment.repository.impl.ProductRepository;
import com.epam.rd.davydova.assignment.service.interfaces.IOrderService;
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
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    /**
     * Add order to database
     *
     * @param productId        product Id
     * @param customerId       customer Id
     * @param orderNumber      order number
     * @param numberOfProducts number of products
     */
    @Override
    public void add(int productId, int customerId, String orderNumber, int numberOfProducts) {
        var orderOptional = orderRepository.findBy(orderNumber);
        var customerOptional = customerRepository.findBy(customerId);
        var productOptional = productRepository.findBy(productId);

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
                    orderRepository.save(order);
                } else {
                    log.error("Product is not added. Order cannot be added");
                }
            } else {
                log.error("Customer is not added. Order cannot be added");
            }
        } else {
            log.error("Order with such a number is already added");
        }
    }

    /**
     * Find order by number
     *
     * @param orderNumber order number
     * @return Optional of order instance
     */
    @Override
    public Optional<Order> findBy(String orderNumber) {
        return orderRepository.findBy(orderNumber);
    }

    /**
     * Find order by Id
     *
     * @param orderId order Id
     * @return Optional of order instance
     */
    @Override
    public Optional<Order> findBy(int orderId) {
        return orderRepository.findBy(orderId);
    }

    /**
     * Find all orders
     *
     * @return Optional of List of orders
     */
    @Override
    public Optional<List> findAll() {
        return orderRepository.findAll();
    }

    /**
     * Update order
     *
     * @param orderId          order Id
     * @param orderNumber      order Number
     * @param productId        product Id
     * @param numberOfProducts quantity of product unit in order
     */
    @Override
    public void update(int orderId, String orderNumber, int productId, int numberOfProducts) {
        var orderOptional = orderRepository.findBy(orderId);
        var productOptional = productRepository.findBy(productId);

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

                if(!order.getProductList().contains(product)) {
                    order.addToList(product);
                    product.addToList(order);
                }

                orderRepository.update(order);
            } else {
                log.error("Product is not present to be added. Order cannot be updated");
            }
        } else {
            log.error("Order is not present to be updated");
        }
    }

    /**
     * Delete order from database
     *
     * @param orderId order Id
     */
    @Override
    public void delete(int orderId) {
        var orderOptional = orderRepository.findBy(orderId);

        if (orderOptional.isPresent()) {
            var order = orderOptional.get();
            orderRepository.delete(order);
        } else {
            log.error("Order is not present to be deleted");
        }
    }

    /**
     * Close OrderRepository
     */
    @Override
    public void close() {
        orderRepository.close();
    }
}
