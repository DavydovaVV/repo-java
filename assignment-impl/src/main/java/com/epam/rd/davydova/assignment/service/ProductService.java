package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.domain.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface ProductService {
    Optional<Product> add(String productName, int supplierId, double unitPrice);

    Optional<Product> findBy(String name);

    Optional<Product> findBy(int id);

    Optional<List> findAll();

    Optional<Product> update(int productId, boolean isDiscontinued);

    boolean delete(int productId);

    void close();
}
