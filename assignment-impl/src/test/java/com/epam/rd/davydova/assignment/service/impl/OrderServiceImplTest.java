package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.repository.CustomerRepository;
import com.epam.rd.davydova.assignment.repository.OrderRepository;
import com.epam.rd.davydova.assignment.repository.ProductRepository;
import com.epam.rd.davydova.assignment.repository.SupplierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * This is a class to test OrderServiceImpl class
 */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private SupplierServiceImpl supplierService;

    private Customer customer = new Customer()
            .setCustomerName("Name")
            .setPhone("1111");

    private Supplier supplier = new Supplier()
            .setCompanyName("KiwiCo")
            .setPhone("2222");

    private Product product = new Product()
            .setProductName("Kiwi")
            .setSupplier(supplier)
            .setUnitPrice(BigDecimal.valueOf(100));

    private Order order = new Order()
            .setOrderNumber("u001")
            .setOrderDate(new Date())
            .setCustomer(customer)
            .setProductList(List.of(product))
            .setTotalAmount(BigDecimal.valueOf(100));

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
        @Bean
        ProductServiceImpl productService(ProductRepository repository) {
            return new ProductServiceImpl(repository);
        }
        @Bean
        SupplierServiceImpl supplierService(SupplierRepository repository) {
            return new SupplierServiceImpl(repository);
        }
    }

    @BeforeAll
    void setup() {
        customerService.add(customer);
        supplierService.add(supplier);
        productService.add(product);
    }

    @Test
    void addNewOrderTest() {
        var addedOrder = orderService.add(order);
        var foundOrder = orderService.findBy(addedOrder.getOrderId()).get();
        Assertions.assertEquals(order.getOrderNumber(), foundOrder.getOrderNumber());
    }

    @Test
    void findPresentOrderByNumberTest() {
        var addedOrder = orderService.add(order);
        var foundOrder = orderService.findBy(addedOrder.getOrderNumber()).get();
        Assertions.assertEquals(order.getOrderNumber(), foundOrder.getOrderNumber());
    }

    @Test
    void findAllOrdersTest() {
        orderService.add(order);
        var foundOrderList = orderService.findAll();
        Assertions.assertEquals(1, foundOrderList.size());
        Assertions.assertEquals(order.getOrderNumber(), foundOrderList.get(0).getOrderNumber());
    }

    @Test
    void updatePresentOrderTest() {
        order.setOrderNumber("u002");
        var updatedOrder = orderService.update(order);
        Assertions.assertEquals("u002", updatedOrder.getOrderNumber());
    }

    @Test
    void deletePresentOrderTest() {
        var addedOrder = orderService.add(order);
        var result = orderService.delete(addedOrder.getOrderId());
        Assertions.assertTrue(result);
    }

    @Test
    void deleteAbsentOrderTest() {
        var result = orderService.delete(1L);
        Assertions.assertFalse(result);
    }
}