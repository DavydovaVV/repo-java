package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.repository.impl.ProductRepository;
import com.epam.rd.davydova.assignment.repository.impl.SupplierRepository;
import com.epam.rd.davydova.assignment.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * This is a class for operations with Product entity and database
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    /**
     * Add product to database
     *
     * @param productName product name
     * @param supplierId  supplier Id
     * @param unitPrice price per unit
     */
    @Override
    public void add(String productName, int supplierId, double unitPrice) {
        var productOptional = productRepository.findBy(productName);
        var supplierOptional = supplierRepository.findBy(supplierId);

        if (productOptional.isEmpty()) {
            if (supplierOptional.isPresent()) {
                var product = new Product();
                var supplier = supplierOptional.get();
                product.setProductName(productName);
                product.setSupplier(supplier);
                product.setUnitPrice(BigDecimal.valueOf(unitPrice));
                productRepository.save(product);
            } else {
                log.error("Supplier is not added. Product cannot be added");
            }
        } else {
            log.error("Product with such a name is already added");
        }
    }

    /**
     * Find product by name
     *
     * @param productName product name
     * @return Optional of product instance
     */
    @Override
    public Optional<Product> findBy(String productName) {
        return productRepository.findBy(productName);
    }

    /**
     * Find product by Id
     *
     * @param productId product Id
     * @return Optional of product instance
     */
    @Override
    public Optional<Product> findBy(int productId) {
        return productRepository.findBy(productId);
    }

    /**
     * Find all products
     *
     * @return Optional of List of products
     */
    @Override
    public Optional<List> findAll() {
        return productRepository.findAll();
    }

    /**
     * Update product status
     *
     * @param productId      product Id
     * @param isDiscontinued product status
     */
    @Override
    public void update(int productId, boolean isDiscontinued) {
        var productOptional = productRepository.findBy(productId);

        if (productOptional.isPresent()) {
            var product = productOptional.get();
            product.setDiscontinued(isDiscontinued);
            productRepository.update(product);
        } else {
            log.error("Product is not present to be updated");
        }
    }

    /**
     * Delete product from database
     *
     * @param productId product Id
     */
    @Override
    public void delete(int productId) {
        var productOptional = productRepository.findBy(productId);

        if (productOptional.isPresent()) {
            var product = productOptional.get();
            productRepository.delete(product);
        } else {
            log.error("Product is not present to be deleted");
        }
    }

    /**
     * Close ProductRepository
     */
    @Override
    public void close() {
        productRepository.close();
    }
}
