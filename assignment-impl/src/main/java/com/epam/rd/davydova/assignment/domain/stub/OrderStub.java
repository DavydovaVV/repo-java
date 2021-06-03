package com.epam.rd.davydova.assignment.domain.stub;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This is a class that defines stubbed Order
 */
@Slf4j
@Getter
@Component
@PropertySource("classpath:properties/stub_order.properties")
public class OrderStub {
    @Value("${order_id}")
    private int orderId;

    @Value("${order_number}")
    private String orderNumber;

    @Value("${customer_id}")
    private int customerId;

    @Value("${order_date}")
    private Date orderDate;

    @Value("${total_amount}")
    private BigDecimal totalAmount;
}