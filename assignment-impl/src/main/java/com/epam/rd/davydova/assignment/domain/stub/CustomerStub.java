package com.epam.rd.davydova.assignment.domain.stub;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * This is a class that defines stubbed Customer
 */
@Slf4j
@Getter
@Component
@PropertySource("classpath:properties/stub_customer.properties")
public class CustomerStub {
    @Value("${customer_id}")
    private int customerId;

    @Value("${customer_name}")
    private String customerName;

    @Value("${customer_phone}")
    private String phone;
}