package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.dto.SupplierDto;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface SupplierService {
    Supplier add(SupplierDto supplierDto);

    Optional<Supplier> findBy(String companyName);

    Optional<Supplier> findBy(long supplierId);

    List<Supplier> findAll();

    Supplier update(SupplierDto supplierDto);

    boolean delete(long supplierId);
}
