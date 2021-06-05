package com.epam.rd.davydova.assignment.config;

import com.epam.rd.davydova.assignment.converter.entity.StringToCustomerConverter;
import com.epam.rd.davydova.assignment.converter.entity.StringToOrderConverter;
import com.epam.rd.davydova.assignment.converter.entity.StringToProductConverter;
import com.epam.rd.davydova.assignment.converter.entity.StringToSupplierConverter;
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
@ComponentScan(basePackages = { "com.epam.rd.davydova.assignment" })
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToCustomerConverter());
        registry.addConverter(new StringToOrderConverter());
        registry.addConverter(new StringToProductConverter());
        registry.addConverter(new StringToSupplierConverter());
    }
}
