package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.dto.OrderDto;
import com.epam.rd.davydova.assignment.service.impl.CustomerServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.OrderServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is a class of CustomerServlet
 */
@Slf4j
@RequiredArgsConstructor
public class OrderResourceImpl implements OrderResource {
    private final OrderServiceImpl orderService;
    private final ProductServiceImpl productService;
    private final CustomerServiceImpl customerService;
    private final ConversionService conversionService;

    /**
     * Add order to database
     *
     * @param orderDto DTO of Order object
     * @return DTO of Order object
     */
    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        var customerId = orderDto.getCustomerId();
        var productIdList = orderDto.getProductIdList();
        var productList = new ArrayList<Product>();
        var totalAmount = BigDecimal.valueOf(0);
        for (long productId : productIdList) {
            var productOptional = productService.findBy(productId);
            productOptional.ifPresent(productList::add);
        }
        var customerOptional = customerService.findBy(customerId);
        if (customerOptional.isPresent()) {
            var customer = customerOptional.get();
            var order = new Order()
                    .setOrderNumber(orderDto.getOrderNumber())
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
            var addedOrder = orderService.add(order);
            orderDto.setOrderId(addedOrder.getOrderId());
            log.info("addOrder() - {}", order);
            return orderDto;
        } else {
            log.error("Customer is not found. Order is not added");
        }
        return null;
    }

        /**
         * Get order(s) from database
         *
         * @param id order Id
         * @return List of OrderDto objects
         */
        @Override
        public List<OrderDto> getOrder (long id){
            List<OrderDto> orderDtoList = new ArrayList<>();
            if (id != 0) {
                var orderOptional = orderService.findBy(id);
                if (orderOptional.isPresent()) {
                    var order = orderOptional.get();
                    var orderDto = conversionService.convert(order, OrderDto.class);
                    orderDtoList.add(orderDto);
                    log.info("getOrder() - {}", order);
                } else {
                    var orderList = orderService.findAll();
                    for (Order order : orderList) {
                        orderDtoList.add(conversionService.convert(order, OrderDto.class));
                    }
                    log.info("getProduct() - {}", orderList);
                }
            }
            return orderDtoList;
        }

    /**
     * Update order in database
     *
     * @param orderDto DTO of Order object
     * @return string result of method
     */
    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        var orderOptional = orderService.findBy(orderDto.getOrderId());
        if (orderOptional.isPresent()) {
            var order = orderOptional.get();
            var customerId = orderDto.getCustomerId();
            var totalAmount = order.getTotalAmount();
            var productIdList = orderDto.getProductIdList();
            var productList = new ArrayList<Product>();
            order.getProductList().clear();
            for (long productId : productIdList) {
                var productOptional = productService.findBy(productId);
                productOptional.ifPresent(productList::add);
            }
            var customerOptional = customerService.findBy(customerId);
            if (customerOptional.isPresent()) {
                var customer = customerOptional.get();
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
                var addedOrder = orderService.update(order);
                orderDto.setOrderId(addedOrder.getOrderId());
                log.info("updateOrder() - {}", order);
                return orderDto;
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
         * @param id order Id
         * @return status of deletion
         */
        @Override
        public HttpStatus deleteOrder (long id){
            var isRemoved = orderService.delete(id);
            if (!isRemoved) {
                return HttpStatus.NOT_FOUND;
            }
            log.info("deleteSupplier() - {}", id);
            return HttpStatus.OK;
        }
    }
