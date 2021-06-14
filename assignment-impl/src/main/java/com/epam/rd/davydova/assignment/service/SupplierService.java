package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface for crud-operations with database
 */
public interface SupplierService {
    /**
     * Add supplier to database
     *
     * @param supplier Supplier object
     * @return Optional of Supplier object
     */
    Supplier add(Supplier supplier);

    /**
     * Find supplier by their name
     *
     * @param companyName company name
     * @return supplier instance
     */
    Optional<Supplier> findBy(String companyName);

    /**
     * Find supplier by Id
     *
     * @param supplierId supplier Id
     * @return Optional of supplier instance
     */
    Optional<Supplier> findBy(Long supplierId);

    /**
     * Find all suppliers
     *
     * @return Optional of List of suppliers
     */
    List<Supplier> findAll();

    /**
     * Update supplier's company name and phone number
     *
     * @param supplier supplier object
     * @return Optional of Supplier object
     */
    Supplier update(Supplier supplier);

    /**
     * Delete supplier from database
     *
     * @param supplierId supplier Id
     */
    boolean delete(Long supplierId);
}
