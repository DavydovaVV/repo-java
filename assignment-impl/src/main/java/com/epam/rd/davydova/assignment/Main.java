package com.epam.rd.davydova.assignment;

import com.epam.rd.davydova.assignment.config.JpaConfig;
import com.epam.rd.davydova.assignment.config.MessageSourceConfig;
import com.epam.rd.davydova.assignment.service.impl.CustomerServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.OrderServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.ProductServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.SupplierServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {

	public static void main (String[] args) {
		var context = new AnnotationConfigApplicationContext(JpaConfig.class, MessageSourceConfig.class);

		var customerService = (CustomerServiceImpl) context.getBean("customerServiceImpl");
		var orderService = (OrderServiceImpl) context.getBean("orderServiceImpl");
		var productService = (ProductServiceImpl) context.getBean("productServiceImpl");
		var supplierService = (SupplierServiceImpl) context.getBean("supplierServiceImpl");

		customerService.add("Wynnie Ramsdale", "56");
		supplierService.add("Simonis", "65-98-63");
		productService.add("Beans", 1, 120);
		orderService.add(1, 1,"u001", 2);

		orderService.update(1, "u0011", 1, 1);
		orderService.update(1, "u0012", 1, 3);

		customerService.close();
	}
}