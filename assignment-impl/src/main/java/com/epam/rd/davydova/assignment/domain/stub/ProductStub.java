package com.epam.rd.davydova.assignment.domain.stub;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * This is a class that defines stubbed Product
 */
@Slf4j
@Getter
@Component
@PropertySource("classpath:properties/stub_product.properties")
public class ProductStub {
    @Value("${product_id}")
    private int productId;

    @Value("${product_name}")
    private String productName;

    @Value("${supplier_id}")
    private int supplierId;

    @Value("${unit_price}")
    private BigDecimal unitPrice;

    @Value("${is_discontinued}")
    private boolean isDiscontinued;
}