package com.epam.rd.davydova.assignment.stub.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * This is a class that defines stubbed Product
 */
@Slf4j
@Data
@Component
@PropertySource("classpath:properties/product.properties")
public class Product {

    @Autowired
    private MessageSource messageSource;

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

    /**
     * Get translated representation of Product object
     *
     * @param languageTag language tag (en-US or ru-RU)
     * @return string representation of Product object
     */
    public String getObject(String languageTag) {
        var result = messageSource.getMessage("message4",
                new Object[]{productId, productName, unitPrice, isDiscontinued},
                "message", Locale.forLanguageTag(languageTag));
        log.info("{}", result);
        return result;
    }
}