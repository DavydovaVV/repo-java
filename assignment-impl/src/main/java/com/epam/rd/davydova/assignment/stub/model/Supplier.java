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
 * This is a class that defines stubbed Supplier
 */
@Slf4j
@Data
@Component
@PropertySource("classpath:properties/supplier.properties")
public class Supplier {

    @Autowired
    private MessageSource messageSource;

    @Value("${supplier_id}")
    private int supplierId;

    @Value("${company_name}")
    private String companyName;

    @Value("${phone}")
    private String phone;

    /**
     * Get translated representation of Supplier object
     *
     * @param languageTag language tag (en-US or ru-RU)
     * @return string representation of Supplier object
     */
    public String getObject(String languageTag) {
        var result = messageSource.getMessage("message3",
                new Object[]{supplierId, companyName, phone}, "message", Locale.forLanguageTag(languageTag));
        log.info("{}", result);
        return result;
    }
}