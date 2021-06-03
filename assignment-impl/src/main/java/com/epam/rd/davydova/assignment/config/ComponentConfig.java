package com.epam.rd.davydova.assignment.config;

import org.springframework.context.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * This is class for configuration of components that use EntityManager
 */
@Configuration
@ComponentScan("com.epam.rd.davydova.assignment.domain")
public class ComponentConfig {

    @Bean
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("PurchasePU")
                .createEntityManager();
    }
}
