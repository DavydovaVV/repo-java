package com.epam.rd.davydova.assignment;

import com.epam.rd.davydova.assignment.config.ComponentConfig;
import com.epam.rd.davydova.assignment.config.LocalBeanConfig;
import com.epam.rd.davydova.assignment.config.MessageSourceConfig;
import com.epam.rd.davydova.assignment.domain.service.CustomerService;
import com.epam.rd.davydova.assignment.stub.model.Customer;
import com.epam.rd.davydova.assignment.stub.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;

@Slf4j
public class Main {

	public static void main (String[] args) {
		var context = new AnnotationConfigApplicationContext(ComponentConfig.class,
				LocalBeanConfig.class, MessageSourceConfig.class);

		var entityManager = (EntityManager) context.getBean("entityManager");

		var customer = (Customer)context.getBean("customer");
		customer.getObject("en-US");

		var order = (Order)context.getBean("order");
		order.getObject("ru-RU");

		var customerService = new CustomerService(entityManager);
		customerService.add("Wynnie Ramsdale","11-12-13");
		customerService.update(1, "010101");
		customerService.findBy(1);
		customerService.findBy("Wynnie Ramsdale");
		customerService.findAll();
		customerService.delete(1);

		entityManager.close();
	}
}