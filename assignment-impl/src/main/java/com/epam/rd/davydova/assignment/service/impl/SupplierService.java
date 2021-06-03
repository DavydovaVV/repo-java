package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.repository.impl.SupplierRepository;
import com.epam.rd.davydova.assignment.service.interfaces.ISupplierService;
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
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;

    /**
     * Add supplier to database
     *
     * @param companyName supplier instance
     */
    @Override
    public void add(String companyName, String phone) {
        var supplierOptional = supplierRepository.findBy(companyName);

        if (supplierOptional.isEmpty()) {
            var supplier = new Supplier();
            supplier.setCompanyName(companyName);
            supplier.setPhone(phone);
            supplierRepository.save(supplier);
        } else {
            log.error("Supplier with such a name is already added");
        }
    }

    /**
     * Find supplier by their name
     *
     * @param companyName company name
     * @return supplier instance
     */
    @Override
    public Optional<Supplier> findBy(String companyName) {
        return supplierRepository.findBy(companyName);
    }

    /**
     * Find supplier by Id
     *
     * @param supplierId supplier Id
     * @return Optional of supplier instance
     */
    @Override
    public Optional<Supplier> findBy(int supplierId) {
        return supplierRepository.findBy(supplierId);
    }

    /**
     * Find all suppliers
     *
     * @return Optional of List of suppliers
     */
    @Override
    public Optional<List> findAll() {
        return supplierRepository.findAll();
    }

    /**
     * Update supplier's company name and phone number
     *
     * @param supplierId supplier Id
     * @param phone      phone number
     */
    @Override
    public void update(int supplierId, String phone) {
        var supplierOptional = supplierRepository.findBy(supplierId);

        if (supplierOptional.isPresent()) {
            var supplier = supplierOptional.get();
            supplier.setPhone(phone);
            supplierRepository.update(supplier);
        } else {
            log.error("Supplier is not present to be updated");
        }
    }

    /**
     * Delete supplier from database
     *
     * @param supplierId supplier Id
     */
    @Override
    public void delete(int supplierId) {
        var supplierOptional = supplierRepository.findBy(supplierId);

        if (supplierOptional.isPresent()) {
            var supplier = supplierOptional.get();
            supplierRepository.delete(supplier);
        } else {
            log.error("Supplier is not present to be deleted");
        }
    }

    /**
     * Close SupplierRepository
     */
    @Override
    public void close() {
        supplierRepository.close();
    }
}
