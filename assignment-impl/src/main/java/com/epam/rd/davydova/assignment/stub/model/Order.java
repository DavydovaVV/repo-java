package com.epam.rd.davydova.assignment.stub.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

/**
 * This is a class that defines stubbed Order
 */
@Slf4j
@Data
@Component
@PropertySource("classpath:properties/order.properties")
public class Order {

    @Autowired
    private MessageSource messageSource;

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


    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Get translated representation of Order object
     *
     * @param languageTag language tag (en-US or ru-RU)
     * @return string representation of Order object
     */
    public String getObject(String languageTag) {
        var result = messageSource.getMessage("message5",
                new Object[]{orderId, orderNumber, orderDate, totalAmount},
                "message", Locale.forLanguageTag(languageTag));
        log.info("{}", result);
        return result;
    }
}