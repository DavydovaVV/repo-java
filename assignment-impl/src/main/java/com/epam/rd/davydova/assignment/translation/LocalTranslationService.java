package com.epam.rd.davydova.assignment.translation;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;

import java.util.Locale;

public interface LocalTranslationService {

    String getTranslation(Locale locale, Customer customer);

    String getTranslation(Locale locale, Supplier supplier);

    String getTranslation(Locale locale, Product product);

    String getTranslation(Locale locale, Order order);
}
