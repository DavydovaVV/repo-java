package com.epam.rd.davydova.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * This is class for configuration of components that use EntityManager
 */
@Configuration
@ComponentScan("com.epam.rd.davydova.assignment")
public class JpaConfig {

    @Bean
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("PurchasePU")
                .createEntityManager();
    }
}
