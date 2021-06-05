package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.dto.SupplierDto;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.repository.SupplierRepository;
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
    private final SupplierRepository supplierRepository;

    /**
     * Add supplier to database
     *
     * @param supplierDto DTO for Supplier object
     * @return Optional of Supplier object
     */
    @Override
    public Supplier add(SupplierDto supplierDto) {
        var supplier = new Supplier();

        supplier.setCompanyName(supplierDto.getCompanyName())
                .setPhone(supplierDto.getPhone());

        return supplierRepository.save(supplier);
    }

    /**
     * Find supplier by their name
     *
     * @param companyName company name
     * @return supplier instance
     */
    @Override
    public Optional<Supplier> findBy(String companyName) {
        return supplierRepository.findByName(companyName);
    }

    /**
     * Find supplier by Id
     *
     * @param supplierId supplier Id
     * @return Optional of supplier instance
     */
    @Override
    public Optional<Supplier> findBy(long supplierId) {
        return supplierRepository.findById(supplierId);
    }

    /**
     * Find all suppliers
     *
     * @return Optional of List of suppliers
     */
    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    /**
     * Update supplier's company name and phone number
     *
     * @param supplierDto DTO for Supplier object
     * @return Optional of Supplier object
     */
    @Override
    public Supplier update(SupplierDto supplierDto) {
        var supplierOptional = supplierRepository.findById(supplierDto.getSupplierId());

        if (supplierOptional.isPresent()) {
            var supplier = supplierOptional.get();

            supplier.setCompanyName(supplierDto.getCompanyName())
                    .setPhone(supplierDto.getPhone());

            return supplierRepository.save(supplier);
        } else {
            log.error("Supplier Id is not found. Supplier is not updated");
        }
        return null;
    }

    /**
     * Delete supplier from database
     *
     * @param supplierId supplier Id
     */
    @Override
    public boolean delete(long supplierId) {
        var supplierOptional = supplierRepository.findById(supplierId);

        if (supplierOptional.isPresent()) {
            var supplier = supplierOptional.get();

            supplierRepository.delete(supplier);

            if (!supplierRepository.existsById(supplierId)) {
                return true;
            }
        } else {
            log.error("Supplier Id is not found. Supplier is not deleted");
        }
        return false;
    }
}
