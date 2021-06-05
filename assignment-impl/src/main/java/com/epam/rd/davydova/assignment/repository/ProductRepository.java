package com.epam.rd.davydova.assignment.repository;

import com.epam.rd.davydova.assignment.domain.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface for operations with database
 */
public interface ProductRepository {
    void save(Product product);

    Optional<Product> findBy(String productName);

    Optional<Product> findBy(int productId);

    Optional<List> findAll();

    void update(Product product);

    void delete(Product product);

    void close();
}
