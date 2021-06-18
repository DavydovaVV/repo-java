package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.TestEntityFactory;
import com.epam.rd.davydova.assignment.repository.SupplierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * This is a class to test SupplierServiceImpl class
 */
@DataJpaTest
class SupplierServiceImplTest {
    @Autowired
    private SupplierServiceImpl supplierService;

    /**
     * Test configuration
     */
    @TestConfiguration
    static class SupplierServiceTestConfiguration {
        @Bean
        SupplierServiceImpl supplierService(SupplierRepository repository) {
            return new SupplierServiceImpl(repository);
        }
    }

    @Test
    public void addNewSupplierTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var addedSupplier = supplierService.add(supplier);
        var foundSupplier = supplierService.findBy(addedSupplier.getSupplierId()).get();
        Assertions.assertEquals(supplier.getCompanyName(), foundSupplier.getCompanyName());
    }

    @Test
    public void findPresentSupplierByNameTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var addedSupplier = supplierService.add(supplier);
        var foundSupplier = supplierService.findBy(addedSupplier.getCompanyName()).get();
        Assertions.assertEquals(supplier.getCompanyName(), foundSupplier.getCompanyName());
    }

    @Test
    public void findAllSuppliersTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        supplierService.add(supplier);
        var foundSupplierList = supplierService.findAll();
        Assertions.assertEquals(1, foundSupplierList.size());
        Assertions.assertEquals(supplier.getCompanyName(), foundSupplierList.get(0).getCompanyName());
    }

    @Test
    public void updatePresentSupplierTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        supplier.setPhone("3333");
        var updatedSupplier = supplierService.update(supplier);
        Assertions.assertEquals("3333", updatedSupplier.getPhone());
    }

    @Test
    public void deletePresentSupplierTest() {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var addedSupplier = supplierService.add(supplier);
        var result = supplierService.delete(addedSupplier.getSupplierId());
        Assertions.assertTrue(result);
    }

    @Test
    public void deleteAbsentSupplierTest() {
        var result = supplierService.delete(1L);
        Assertions.assertFalse(result);
    }
}