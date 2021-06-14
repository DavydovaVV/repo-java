package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.repository.ProductRepository;
import com.epam.rd.davydova.assignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a class for operations with Product entity and database
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    /**
     * Add product to database
     *
     * @param product Product object
     * @return Optional of Product object
     */
    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    /**
     * Find product by name
     *
     * @param productName product name
     * @return Optional of product instance
     */
    @Override
    public Optional<Product> findBy(String productName) {
        return productRepository.findByProductName(productName);
    }

    /**
     * Find product by Id
     *
     * @param productId product Id
     * @return Optional of product instance
     */
    @Override
    public Optional<Product> findBy(Long productId) {
        return productRepository.findById(productId);
    }

    /**
     * Find all products
     *
     * @return Optional of List of products
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Update product status
     *
     * @param product product object
     * @return Optional of Product object
     */
    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    /**
     * Delete product from database
     *
     * @param productId product Id
     * @return status of deletion
     */
    @Override
    public boolean delete(Long productId) {
        var productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            var product = productOptional.get();
            productRepository.delete(product);
            return !productRepository.existsById(productId);
        } else {
            log.error("Product Id is not found. Product is not deleted");
        }
        return false;
    }
}
