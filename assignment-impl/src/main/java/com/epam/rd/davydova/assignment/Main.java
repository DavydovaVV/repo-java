package com.epam.rd.davydova.assignment;

import com.epam.rd.davydova.assignment.domain.service.CustomerService;
import com.epam.rd.davydova.assignment.domain.service.OrderService;
import com.epam.rd.davydova.assignment.domain.service.ProductService;
import com.epam.rd.davydova.assignment.domain.service.SupplierService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main (String[] args) {
		var customerService = new CustomerService();
		var supplierService = new SupplierService();
		var productService = new ProductService();
		var orderService = new OrderService();

		//added 5 customers
		customerService.add("Wynnie Ramsdale","11-12-13");
		customerService.add("Dolley Paten","15-17-18");
		customerService.add("Charmaine Ayllett","15-16-12");
		customerService.add("Ernaline McSweeney","10-18-10");
		customerService.add("Afton Conradie","16-10-17");

		//added 5 suppliers
		supplierService.add("Simonis", "12-65-88");
		supplierService.add("Kuhn", "34-67-95");
		supplierService.add("Windler", "69-35-78");
		supplierService.add("Stroman", "35-94-82");
		supplierService.add("Koch", "32-65-90");

		//added 5 products
		productService.add("Snails",1, 150);
		productService.add("Beans", 2, 5);
		productService.add("Towel", 3, 9);
		productService.add("Papayas", 4, 50);
		productService.add("Cheese", 5, 25);

		//added 5 orders
		orderService.add(1,1,3);
		orderService.add(2,3,1);
		orderService.add(3,1,1);
		orderService.add(4,4,1);
		orderService.add(5, 2,2);

		//updated: customer no.2, supplier no.2, product no.5, order no.2
		customerService.update(2, "010101");
		supplierService.update(2, "12345");
		productService.update(5, true);
		orderService.update(2,"u_001", 2,2);

		//deleted: customer no.1 (cascade removal of order no.1), supplier no.5 (cascade removal of product no.5),
		//product no.4, order no.1 (should catch exception as customer no.1 was deleted)
		customerService.delete(1);
		supplierService.delete(5);
		productService.delete(4);
		orderService.delete(1);

		customerService.findBy(1);
		customerService.findAll();
		customerService.close();
	}
}