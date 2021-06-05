package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.repository.impl.SupplierRepositoryImpl;
import com.epam.rd.davydova.assignment.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a class for operations with Supplier entity and database
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepositoryImpl supplierRepositoryImpl;

    /**
     * Add supplier to database
     *
     * @param companyName supplier instance
     */
    @Override
    public Optional<Supplier> add(String companyName, String phone) {
        var supplierOptional = supplierRepositoryImpl.findBy(companyName);

        if (supplierOptional.isEmpty()) {
            var supplier = new Supplier();
            supplier.setCompanyName(companyName);
            supplier.setPhone(phone);
            supplierRepositoryImpl.save(supplier);
            return supplierRepositoryImpl.findBy(supplier.getSupplierId());
        } else {
            log.error("Supplier with such a name is already added");
        }
        return Optional.empty();
    }

    /**
     * Find supplier by their name
     *
     * @param companyName company name
     * @return supplier instance
     */
    @Override
    public Optional<Supplier> findBy(String companyName) {
        return supplierRepositoryImpl.findBy(companyName);
    }

    /**
     * Find supplier by Id
     *
     * @param supplierId supplier Id
     * @return Optional of supplier instance
     */
    @Override
    public Optional<Supplier> findBy(int supplierId) {
        return supplierRepositoryImpl.findBy(supplierId);
    }

    /**
     * Find all suppliers
     *
     * @return Optional of List of suppliers
     */
    @Override
    public Optional<List> findAll() {
        return supplierRepositoryImpl.findAll();
    }

    /**
     * Update supplier's company name and phone number
     *
     * @param supplierId supplier Id
     * @param phone      phone number
     */
    @Override
    public Optional<Supplier> update(int supplierId, String phone) {
        var supplierOptional = supplierRepositoryImpl.findBy(supplierId);

        if (supplierOptional.isPresent()) {
            var supplier = supplierOptional.get();
            supplier.setPhone(phone);
            supplierRepositoryImpl.update(supplier);
            return supplierRepositoryImpl.findBy(supplierId);
        } else {
            log.error("Supplier is not present to be updated");
        }
        return Optional.empty();
    }

    /**
     * Delete supplier from database
     *
     * @param supplierId supplier Id
     */
    @Override
    public boolean delete(int supplierId) {
        var supplierOptional = supplierRepositoryImpl.findBy(supplierId);

        if (supplierOptional.isPresent()) {
            var supplier = supplierOptional.get();
            supplierRepositoryImpl.delete(supplier);
            return supplierRepositoryImpl.findBy(supplierId).isEmpty();
        } else {
            log.error("Supplier is not present to be deleted");
        }
        return false;
    }

    /**
     * Close SupplierRepository
     */
    @Override
    public void close() {
        supplierRepositoryImpl.close();
    }
}
