package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.repository.SupplierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@DataJpaTest
class SupplierServiceImplTest {
    @Autowired
    private SupplierServiceImpl supplierService;

    private Supplier supplier = new Supplier()
            .setCompanyName("KiwiCo")
            .setPhone("2222");

    @TestConfiguration
    static class SupplierServiceTestConfiguration {
        @Bean
        SupplierServiceImpl supplierService(SupplierRepository repository) {
            return new SupplierServiceImpl(repository);
        }
    }

    @Test
    void addNewSupplierTest() {
        var addedSupplier = supplierService.add(supplier);
        var foundSupplier = supplierService.findBy(addedSupplier.getSupplierId()).get();
        Assertions.assertEquals(supplier.getCompanyName(), foundSupplier.getCompanyName());
    }

    @Test
    void findPresentSupplierByNameTest() {
        var addedSupplier = supplierService.add(supplier);
        var foundSupplier = supplierService.findBy(addedSupplier.getCompanyName()).get();
        Assertions.assertEquals(supplier.getCompanyName(), foundSupplier.getCompanyName());
    }

    @Test
    void findAllSuppliersTest() {
        var addedSupplier = supplierService.add(supplier);
        var foundSupplierList = supplierService.findAll();
        Assertions.assertEquals(1, foundSupplierList.size());
        Assertions.assertEquals(supplier.getCompanyName(), foundSupplierList.get(0).getCompanyName());
    }

    @Test
    void updatePresentSupplierTest() {
        supplier.setPhone("3333");
        var updatedSupplier = supplierService.update(supplier);
        Assertions.assertEquals("3333", updatedSupplier.getPhone());
    }

    @Test
    void deletePresentSupplierTest() {
        var addedSupplier = supplierService.add(supplier);
        var result = supplierService.delete(addedSupplier.getSupplierId());
        Assertions.assertTrue(result);
    }

    @Test
    void deleteAbsentSupplierTest() {
        var result = supplierService.delete(1L);
        Assertions.assertFalse(result);
    }
}