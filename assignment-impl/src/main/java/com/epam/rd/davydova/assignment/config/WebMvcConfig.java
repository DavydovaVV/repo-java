package com.epam.rd.davydova.assignment.config;

import com.epam.rd.davydova.assignment.converter.CustomerToDtoConverter;
import com.epam.rd.davydova.assignment.converter.OrderToDtoConverter;
import com.epam.rd.davydova.assignment.converter.ProductToDtoConverter;
import com.epam.rd.davydova.assignment.converter.SupplierToDtoConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MvcConfig
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.epam.rd.davydova.assignment")
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CustomerToDtoConverter());
        registry.addConverter(new OrderToDtoConverter());
        registry.addConverter(new ProductToDtoConverter());
        registry.addConverter(new SupplierToDtoConverter());
    }
}
