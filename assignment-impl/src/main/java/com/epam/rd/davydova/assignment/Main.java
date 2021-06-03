package com.epam.rd.davydova.assignment;

import com.epam.rd.davydova.assignment.config.ComponentConfig;
import com.epam.rd.davydova.assignment.config.MessageSourceConfig;
import com.epam.rd.davydova.assignment.service.impl.CustomerService;
import com.epam.rd.davydova.assignment.service.impl.OrderService;
import com.epam.rd.davydova.assignment.service.impl.ProductService;
import com.epam.rd.davydova.assignment.service.impl.SupplierService;
import com.epam.rd.davydova.assignment.service.stub.impl.CustomerServiceStub;
import com.epam.rd.davydova.assignment.service.stub.impl.OrderServiceStub;
import com.epam.rd.davydova.assignment.service.stub.impl.ProductServiceStub;
import com.epam.rd.davydova.assignment.service.stub.impl.SupplierServiceStub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {

	public static void main (String[] args) {
		var context = new AnnotationConfigApplicationContext(ComponentConfig.class, MessageSourceConfig.class);

		//Profile local
		var customerServiceStub = (CustomerServiceStub) context.getBean("customerServiceStub");
		var orderServiceStub = (OrderServiceStub) context.getBean("orderServiceStub");
		var productServiceStub = (ProductServiceStub) context.getBean("productServiceStub");
		var supplierServiceStub = (SupplierServiceStub) context.getBean("supplierServiceStub");

		customerServiceStub.findAll("ru-RU");
		orderServiceStub.findAll("ru-RU");
		productServiceStub.findAll("ru-RU");
		supplierServiceStub.findAll("ru-RU");

		customerServiceStub.findAll("en-US");
		orderServiceStub.findAll("en-US");
		productServiceStub.findAll("en-US");
		supplierServiceStub.findAll("en-US");

		//Profile !local
		var customerService = (CustomerService)context.getBean("customerService");
		var supplierService = (SupplierService)context.getBean("supplierService");
		var productService = (ProductService)context.getBean("productService");
		var orderService = (OrderService)context.getBean("orderService");

		customerService.add("Berni Brill", "12-32-52");
		supplierService.add("Simonis", "65-98-63");
		productService.add("Beans", 1, 120);
		orderService.add(1, 1,"u001", 2);

		orderService.update(1, "u0011", 1, 1);
	}
}