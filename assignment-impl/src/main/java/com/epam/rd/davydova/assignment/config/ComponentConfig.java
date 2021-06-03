package com.epam.rd.davydova.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * This is class for configuration of components that use EntityManager
 */
@Configuration
@Profile("!local")
@ComponentScan("com.epam.rd.davydova.assignment")
public class ComponentConfig {

    @Bean
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("PurchasePU")
                .createEntityManager();
    }
}
