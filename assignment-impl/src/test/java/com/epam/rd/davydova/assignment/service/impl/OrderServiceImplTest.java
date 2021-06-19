package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.TestEntityFactory;
import com.epam.rd.davydova.assignment.repository.CustomerRepository;
import com.epam.rd.davydova.assignment.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * This is a class to test OrderServiceImpl class
 */
@DataJpaTest
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private CustomerServiceImpl customerService;

    /**
     * Test configuration
     */
    @TestConfiguration
    static class CustomerServiceTestConfiguration {
        @Bean
        CustomerServiceImpl customerService(CustomerRepository repository) {
            return new CustomerServiceImpl(repository);
        }
        @Bean
        OrderServiceImpl orderService(OrderRepository repository) {
            return new OrderServiceImpl(repository);
        }
    }

    @Test
    public void addNewOrderTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var order = testEntityFactory.createTestOrder();
        var addedCustomer = customerService.add(customer);
        var addedOrder = orderService.add(order.setCustomer(addedCustomer));
        var foundOrder = orderService.findBy(addedOrder.getOrderId()).get();
        Assertions.assertEquals(order.getOrderNumber(), foundOrder.getOrderNumber());
    }

    @Test
    public void findPresentOrderByNumberTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var order = testEntityFactory.createTestOrder();
        var addedCustomer = customerService.add(customer);
        var addedOrder = orderService.add(order.setCustomer(addedCustomer));
        var foundOrder = orderService.findBy(addedOrder.getOrderNumber()).get();
        Assertions.assertEquals(order.getOrderNumber(), foundOrder.getOrderNumber());
    }

    @Test
    public void findAllOrdersTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var order = testEntityFactory.createTestOrder();
        var addedCustomer = customerService.add(customer);
        orderService.add(order.setCustomer(addedCustomer));
        var foundOrderList = orderService.findAll();
        Assertions.assertEquals(1, foundOrderList.size());
        Assertions.assertEquals(order.getOrderNumber(), foundOrderList.get(0).getOrderNumber());
    }

    @Test
    public void updatePresentOrderTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var order = testEntityFactory.createTestOrder();
        var addedCustomer = customerService.add(customer);
        var addedOrder = orderService.add(order.setCustomer(addedCustomer));
        addedOrder.setOrderNumber("u002");
        var updatedOrder = orderService.update(addedOrder);
        Assertions.assertEquals("u002", updatedOrder.getOrderNumber());
    }

    @Test
    public void deletePresentOrderTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var order = testEntityFactory.createTestOrder();
        var addedCustomer = customerService.add(customer);
        var addedOrder = orderService.add(order.setCustomer(addedCustomer));
        var result = orderService.delete(addedOrder.getOrderId());
        Assertions.assertTrue(result);
    }

    @Test
    public void deleteAbsentOrderTest() {
        var result = orderService.delete(1L);
        Assertions.assertFalse(result);
    }
}