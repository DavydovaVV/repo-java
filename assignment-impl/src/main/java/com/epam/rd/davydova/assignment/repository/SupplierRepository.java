package com.epam.rd.davydova.assignment.repository;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface for operations with database
 */
public interface SupplierRepository {
    void save(Supplier supplier);

    Optional<Supplier> findBy(String companyName);

    Optional<Supplier> findBy(int supplierId);

    Optional<List> findAll();

    void update(Supplier supplier);

    void delete(Supplier supplier);

    void close();
}
