package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.TestEntityFactory;
import com.epam.rd.davydova.assignment.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

/**
 * This is a class to test CustomerServiceImpl class
 */
@DataJpaTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @TestConfiguration
    static class CustomerServiceTestConfiguration {
        @Bean
        CustomerServiceImpl customerServiceImpl(CustomerRepository repository) {
            return new CustomerServiceImpl(repository);
        }
    }

    @Test
    public void addNewCustomerTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var addedCustomer = customerServiceImpl.add(customer);
        var foundCustomer = customerServiceImpl
                .findBy(addedCustomer.getCustomerId())
                .get();
        Assertions.assertEquals(customer.getCustomerName(), foundCustomer.getCustomerName());
    }

    @Test
    public void findPresentCustomerByNameTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        customerServiceImpl.add(customer);
        var foundCustomer = customerServiceImpl.findBy("Name").get();
        Assertions.assertEquals(customer.getCustomerName(), foundCustomer.getCustomerName());
    }

    @Test
    public void findAllCustomersTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        customerServiceImpl.add(customer);
        var foundCustomerList = customerServiceImpl.findAll();
        Assertions.assertEquals(1, foundCustomerList.size());
        Assertions.assertEquals("Name", foundCustomerList.get(0).getCustomerName());
    }

    @Test
    public void updatePresentCustomerTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        customer.setPhone("2222");
        var updatedCustomer = customerServiceImpl.update(customer);
        Assertions.assertEquals("2222", updatedCustomer.getPhone());
    }

    @Test
    public void deletePresentCustomerTest() {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var addedCustomer = customerServiceImpl.add(customer);
        var result = customerServiceImpl.delete(addedCustomer.getCustomerId());
        Assertions.assertTrue(result);
        Assertions.assertEquals(Optional.empty(), customerServiceImpl.findBy("Name"));
    }

    @Test
    public void deleteAbsentCustomerTest() {
        var result = customerServiceImpl.delete(1L);
        Assertions.assertFalse(result);
    }
}