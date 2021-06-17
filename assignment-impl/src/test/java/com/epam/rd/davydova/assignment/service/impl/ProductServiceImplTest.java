package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.repository.ProductRepository;
import com.epam.rd.davydova.assignment.repository.SupplierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@DataJpaTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private SupplierServiceImpl supplierService;

    private Supplier supplier = new Supplier()
            .setCompanyName("KiwiCo")
            .setPhone("2222");

    private Product product = new Product()
            .setProductName("Kiwi")
            .setSupplier(supplier)
            .setUnitPrice(BigDecimal.valueOf(100));

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
    void addNewProductTest() {
        supplierService.add(supplier);
        var addedProduct = productService.add(product);
        var foundProduct = productService.findBy(addedProduct.getProductId()).get();
        Assertions.assertEquals(product.getProductName(), foundProduct.getProductName());
    }


    @Test
    void findPresentProductByNameTest() {
        supplierService.add(supplier);
        var addedProduct = productService.add(product);
        var foundProduct = productService.findBy(addedProduct.getProductName()).get();
        Assertions.assertEquals(product.getProductName(), foundProduct.getProductName());
    }

    @Test
    void findAllProductsTest() {
        supplierService.add(supplier);
        productService.add(product);
        var foundProductList = productService.findAll();
        Assertions.assertEquals(1, foundProductList.size());
        Assertions.assertEquals(product.getProductName(), foundProductList.get(0).getProductName());
    }

    @Test
    void updatePresentProductTest() {
        supplierService.add(supplier);
        product.setDiscontinued(true);
        var updatedProduct = productService.update(product);
        var foundProduct = productService.findBy(updatedProduct.getProductId()).get();
        Assertions.assertEquals(product.getProductName(), foundProduct.getProductName());
    }

    @Test
    void deletePresentProductTest() {
        supplierService.add(supplier);
        var addedProduct = productService.add(product);
        var result = productService.delete(addedProduct.getProductId());
        Assertions.assertTrue(result);
    }

    @Test
    void deleteAbsentProductTest() {
        var result = productService.delete(1L);
        Assertions.assertFalse(result);
    }
}