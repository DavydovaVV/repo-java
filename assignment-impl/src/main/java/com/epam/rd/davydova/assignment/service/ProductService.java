package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.dto.ProductDto;
import com.epam.rd.davydova.assignment.domain.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that wraps interface for crud-operations with database
 */
public interface ProductService {
    Product add(ProductDto productDto);

    Optional<Product> findBy(String name);

    Optional<Product> findBy(long productId);

    List<Product> findAll();

    Product update(ProductDto productDto);

    boolean delete(long productId);
}
