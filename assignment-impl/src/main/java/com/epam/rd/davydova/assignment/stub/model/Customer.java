package com.epam.rd.davydova.assignment.stub.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * This is a class that defines stubbed Customer
 */
@Slf4j
@Data
@Component
@PropertySource("classpath:properties/customer.properties")
public class Customer {

    @Autowired
    private MessageSource messageSource;

    @Value("${customer_id}")
    private int customerId;

    @Value("${customer_name}")
    private String customerName;

    @Value("${phone}")
    private String phone;

    /**
     * Get translated representation of Customer object
     *
     * @param languageTag language tag (en-US or ru-RU)
     * @return string representation of Customer object
     */
    public String getObject(String languageTag) {
        var result = messageSource.getMessage("message2",
                new Object[]{customerId, customerName, phone}, "message", Locale.forLanguageTag(languageTag));
        log.info("{}", result);
        return result;
    }
}