package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.domain.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface for crud-operations with database
 */
public interface ProductService {
    /**
     * Add product to database
     *
     * @param product Product object
     * @return Optional of Product object
     */
    Product add(Product product);

    /**
     * Find product by name
     *
     * @param productName product name
     * @return Optional of product instance
     */
    Optional<Product> findBy(String productName);

    /**
     * Find product by Id
     *
     * @param productId product Id
     * @return Optional of product instance
     */
    Optional<Product> findBy(Long productId);

    /**
     * Find all products
     *
     * @return Optional of List of products
     */
    List<Product> findAll();

    /**
     * Update product status
     *
     * @param product Product object
     * @return Optional of Product object
     */
    Product update(Product product);

    /**
     * Delete product from database
     *
     * @param productId product Id
     * @return status of deletion
     */
    boolean delete(Long productId);
}
