package com.epam.rd.davydova.assignment.translation;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * This is a class to get stubbed entities translated into a language
 */
@Slf4j
@Service
@Profile("local")
@RequiredArgsConstructor
public class LocalTranslationServiceImpl implements LocalTranslationService{
    private final MessageSource messageSource;
    private final LocalTranslationDto dto;

    /**
     * Get translation for stubbed Customer object
     * @param locale locale
     * @param customer Customer object
     * @return String representation of Customer object translated into a language
     */
    @Override
    public String getTranslation(Locale locale, Customer customer) {
        var values = dto.getCustomerValues();

        var result = messageSource.getMessage("message2",
                new Object[]{values.get(0), values.get(1)},
                "message", locale);

        log.info("{}", result);

        return result;
    }

    /**
     * Get translation for stubbed Supplier object
     * @param locale locale
     * @param supplier Supplier object
     * @return String representation of Supplier object translated into a language
     */
    @Override
    public String getTranslation(Locale locale, Supplier supplier) {
        var values = dto.getSupplierValues();

        var result = messageSource.getMessage("message3",
                new Object[]{values.get(0), values.get(1)},
                "message", locale);

        log.info("{}", result);

        return result;
    }

    /**
     * Get translation for stubbed Product object
     * @param locale locale
     * @param product Product object
     * @return String representation of Product object translated into a language
     */
    @Override
    public String getTranslation(Locale locale, Product product) {
        var values = dto.getProductValues();

        var result = messageSource.getMessage("message4",
                new Object[]{values.get(0), values.get(1), values.get(2),
                        values.get(3), values.get(4)},
                "message", locale);

        log.info("{}", result);

        return result;
    }

    /**
     * Get translation for stubbed Order object
     * @param locale locale
     * @param order Order object
     * @return String representation of Order object translated into a language
     */
    @Override
    public String getTranslation(Locale locale, Order order) {
        var values = dto.getOrderValues();

        var result = messageSource.getMessage("message5",
                new Object[]{values.get(0), values.get(1), values.get(2),
                        values.get(3), values.get(4), values.get(5),
                        values.get(6), values.get(7), values.get(8),
                        values.get(9), values.get(10)},
                "message", locale);

        log.info("{}", result);

        return result;
    }
}
