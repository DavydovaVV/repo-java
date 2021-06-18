package com.epam.rd.davydova.assignment;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Creates test entity objects
 */
public class TestEntityFactory {
    @Autowired
    CustomerServiceImpl customerService;

    /**
     * Create Customer object for test
     *
     * @return Customer object
     */
    public Customer createTestCustomer() {
        return new Customer()
                .setCustomerId(1L)
                .setCustomerName("Name")
                .setPhone("1111");
    }

    /**
     * Create Supplier object for test
     *
     * @return Supplier object
     */
    public Supplier createTestSupplier() {
        return new Supplier()
                .setSupplierId(1L)
                .setCompanyName("KiwiCo")
                .setPhone("2222");
    }

    /**
     * Create Product object for test
     *
     * @return Product object
     */
    public Product createTestProduct() {
        return new Product()
                .setProductId(1L)
                .setSupplier(createTestSupplier())
                .setProductName("Kiwi")
                .setUnitPrice(BigDecimal.valueOf(100));
    }

    /**
     * Create Order object for test
     *
     * @return Order object
     */
    public Order createTestOrder() {
        var productList = new ArrayList<Product>();
        productList.add(createTestProduct());
        return new Order()
                .setOrderId(1L)
                .setOrderNumber("u001")
                .setOrderDate(new Date())
                .setCustomer(createTestCustomer())
                .setProductList(productList)
                .setTotalAmount(BigDecimal.valueOf(100));
    }
}
