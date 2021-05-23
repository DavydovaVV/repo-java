package com.epam.rd.davydova.assignment;

import com.epam.rd.davydova.assignment.domain.service.ExecutionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	
	public static void main (String[] args) {
		var executionService = new ExecutionService();

		executionService.addSupplier("Simonis");
		executionService.addSupplier("Kuhn");
		executionService.addSupplier("Windler");
		executionService.addSupplier("Stroman");

		executionService.addCustomer("Wynnie Ramsdale");
		executionService.addCustomer("Dolley Paten");
		executionService.addCustomer("Charmaine Ayllett");
		executionService.addCustomer("Ernaline McSweeney");

		executionService.addProduct("Snails","Simonis", 150);
		executionService.addProduct("Wine", "Kuhn", 100);
		executionService.addProduct("Beans", "Windler", 5);
		executionService.addProduct("Towel", "Stroman", 9);
		executionService.addOrder("Wynnie Ramsdale", "Snails", 550);
		executionService.addOrder("Dolley Paten", "Wine", 200);
		executionService.addOrder("Charmaine Ayllett", "Beans", 15);
		executionService.addOrder("Ernaline McSweeney","Towel", 18);


		executionService.closeEntityManagerFactory();

	}

}
