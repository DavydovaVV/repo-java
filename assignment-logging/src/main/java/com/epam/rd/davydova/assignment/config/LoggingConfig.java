package com.epam.rd.davydova.assignment.config;

import com.epam.rd.davydova.assignment.advice.LoggingAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a class of LoggingAdvice configuration
 */
@Configuration
@ConditionalOnProperty(prefix = "logger", name = "enabled", havingValue = "true")
public class LoggingConfig {

    @Bean
    public LoggingAdvice loggingAdvice() {
        return new LoggingAdvice();
    }
}

