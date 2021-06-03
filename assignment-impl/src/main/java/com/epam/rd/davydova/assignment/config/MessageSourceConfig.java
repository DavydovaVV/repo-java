package com.epam.rd.davydova.assignment.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * This is class for configuration of internationalization
 */
@Configuration
@Profile("local")
@ComponentScan("com.epam.rd.davydova.assignment.service.stub " +
        "&& com.epam.rd.davydova.assignment.domain.stub")
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        reloadableResourceBundleMessageSource.setBasename("classpath:messages/message");
        return reloadableResourceBundleMessageSource;
    }
}
