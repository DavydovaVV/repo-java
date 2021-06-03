package com.epam.rd.davydova.assignment.service.interfaces;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface ISupplierService {
    void add(String companyName, String phone);

    Optional<Supplier> findBy(String companyName);

    Optional<Supplier> findBy(int supplierId);

    Optional<List> findAll();

    void update(int supplierId, String phone);

    void delete(int supplierId);

    void close();
}
