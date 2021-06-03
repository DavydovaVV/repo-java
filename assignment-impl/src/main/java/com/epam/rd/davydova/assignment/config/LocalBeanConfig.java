package com.epam.rd.davydova.assignment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * This is class for configuration of components that use stubbed repository
 */
@Configuration
@Profile("local")
@ComponentScan("com.epam.rd.davydova.assignment.stub")
public class LocalBeanConfig {

}
