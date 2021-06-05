package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.dto.OrderDto;
import com.epam.rd.davydova.assignment.repository.CustomerRepository;
import com.epam.rd.davydova.assignment.repository.OrderRepository;
import com.epam.rd.davydova.assignment.repository.ProductRepository;
import com.epam.rd.davydova.assignment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    /**
     * Add order to database
     *
     * @param orderDto DTO for Order object
     * @return Optional of Order object
     */
    @Override
    public Order add(OrderDto orderDto) {
        var order = new Order();
        var customerId = orderDto.getCustomerId();
        var productIdList = orderDto.getProductIdList();
        var productList = new ArrayList<Product>();
        var totalAmount = BigDecimal.valueOf(0);

        for (long productId : productIdList) {
            productList.add(productRepository.getById(productId));
        }

        if (customerRepository.existsById(customerId)) {
            var customer = customerRepository.getById(customerId);

            order.setOrderNumber(orderDto.getOrderNumber())
                    .setCustomer(customer)
                    .setProductList(productList)
                    .setOrderDate(new Date());

            List<Order> orderList;

            for (Product product : productList) {
                totalAmount = totalAmount.add(product.getUnitPrice());

                if (!(orderList = product.getOrderList()).contains(order)) {
                    orderList.add(order);
                } else {
                    log.debug("Order is already added for this product");
                }
            }

            order.setTotalAmount(totalAmount);

            customer.getOrderList().add(order);

            return orderRepository.save(order);
        }

        return null;
    }

    /**
     * Find order by number
     *
     * @param orderNumber order number
     * @return Optional of order instance
     */
    @Override
    public Optional<Order> findBy(String orderNumber) {
        return orderRepository.findByNumber(orderNumber);
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
     * @param orderDto DTO for Order object
     * @return Optional of Order object
     */
    @Override
    public Order update(OrderDto orderDto) {
        var orderOptional = orderRepository.findById(orderDto.getOrderId());

        if (orderOptional.isPresent()) {
            var order = orderOptional.get();
            var customerId = orderDto.getCustomerId();
            var totalAmount = order.getTotalAmount();

            var productIdList = orderDto.getProductIdList();
            var productList = new ArrayList<Product>();
            order.getProductList().clear();

            for (long productId : productIdList) {
                productList.add(productRepository.getById(productId));
            }

            if (customerRepository.existsById(customerId)) {
                var customer = customerRepository.getById(customerId);


                order.setOrderNumber(orderDto.getOrderNumber())
                        .setCustomer(customer)
                        .setProductList(productList);

                List<Order> orderList;

                for (Product product : productList) {
                    totalAmount = totalAmount.add(product.getUnitPrice());

                    if (!(orderList = product.getOrderList()).contains(order)) {
                        orderList.add(order);
                    } else {
                        log.debug("Order is already added for this product");
                    }
                }

                order.setTotalAmount(totalAmount);
                customer.getOrderList().add(order);

                return orderRepository.save(order);
            } else {
                log.error("Customer Id is not found. Order is not updated");
            }
        } else {
            log.error("Order Id is not found. Order is not updated");
        }
        return null;
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

            if (!orderRepository.existsById(orderId)) {
                return true;
            }
        } else {
            log.error("Order Id is not found. Order is not deleted");
        }
        return false;
    }
}
