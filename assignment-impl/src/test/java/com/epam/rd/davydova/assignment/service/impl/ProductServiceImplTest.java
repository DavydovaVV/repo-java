package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.TestEntityFactory;
import com.epam.rd.davydova.assignment.repository.ProductRepository;
import com.epam.rd.davydova.assignment.repository.SupplierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * This is a class to test ProductServiceImpl class
 */
@DataJpaTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private SupplierServiceImpl supplierService;

    /**
     * Test configuration
     */
    @TestConfiguration
    static class ProductServiceTestConfiguration {
        @Bean
        ProductServiceImpl productService(ProductRepository repository) {
            return new ProductServiceImpl(repository);
        }
        @Bean
        SupplierServiceImpl supplierService(SupplierRepository repository) {
            return new SupplierServiceImpl(repository);
        }
    }

    @Test
    public void addNewProductTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var product = testEntityFactory.createTestProduct();
        var addedSupplier = supplierService.add(supplier);
        var addedProduct = productService.add(product.setSupplier(addedSupplier));
        var foundProduct = productService.findBy(addedProduct.getProductId()).get();
        Assertions.assertEquals(product.getProductName(), foundProduct.getProductName());
    }


    @Test
    public void findPresentProductByNameTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var product = testEntityFactory.createTestProduct();
        var addedSupplier = supplierService.add(supplier);
        var addedProduct = productService.add(product.setSupplier(addedSupplier));
        var foundProduct = productService.findBy(addedProduct.getProductName()).get();
        Assertions.assertEquals(product.getProductName(), foundProduct.getProductName());
    }

    @Test
    public void findAllProductsTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var product = testEntityFactory.createTestProduct();
        var addedSupplier = supplierService.add(supplier);
        productService.add(product.setSupplier(addedSupplier));
        var foundProductList = productService.findAll();
        Assertions.assertEquals(1, foundProductList.size());
        Assertions.assertEquals(product.getProductName(), foundProductList.get(0).getProductName());
    }

    @Test
    public void updatePresentProductTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var product = testEntityFactory.createTestProduct();
        var addedSupplier = supplierService.add(supplier);
        var addedProduct = productService.add(product.setSupplier(addedSupplier));
        var updatedProduct = productService.update(addedProduct);
        var foundProduct = productService.findBy(updatedProduct.getProductId()).get();
        Assertions.assertEquals(product.getProductName(), foundProduct.getProductName());
    }

    @Test
    public void deletePresentProductTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var product = testEntityFactory.createTestProduct();
        var addedSupplier = supplierService.add(supplier);
        var addedProduct = productService.add(product.setSupplier(addedSupplier));
        var result = productService.delete(addedProduct.getProductId());
        Assertions.assertTrue(result);
    }

    @Test
    public void deleteAbsentProductTest() {
        var result = productService.delete(1L);
        Assertions.assertFalse(result);
    }
}