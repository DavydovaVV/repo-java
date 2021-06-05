package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.repository.impl.ProductRepositoryImpl;
import com.epam.rd.davydova.assignment.repository.impl.SupplierRepositoryImpl;
import com.epam.rd.davydova.assignment.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepositoryImpl productRepositoryImpl;
    private final SupplierRepositoryImpl supplierRepositoryImpl;

    /**
     * Add product to database
     *
     * @param productName product name
     * @param supplierId  supplier Id
     * @param unitPrice price per unit
     */
    @Override
    public Optional<Product> add(String productName, int supplierId, double unitPrice) {
        var productOptional = productRepositoryImpl.findBy(productName);
        var supplierOptional = supplierRepositoryImpl.findBy(supplierId);

        if (productOptional.isEmpty()) {
            if (supplierOptional.isPresent()) {
                var supplier = supplierOptional.get();
                if (supplier.getProductList().isEmpty()) {
                    var product = new Product();
                    product.setProductName(productName);
                    product.setSupplier(supplier);
                    product.setUnitPrice(BigDecimal.valueOf(unitPrice));
                    productRepositoryImpl.save(product);
                    return productRepositoryImpl.findBy(product.getProductId());
                } else {
                    log.error("Supplier supplies another product. Product cannot be added");
                }
            } else {
                log.error("Supplier is not added. Product cannot be added");
            }
        } else {
            log.error("Product with such a name is already added");
        }
        return Optional.empty();
    }

    /**
     * Find product by name
     *
     * @param productName product name
     * @return Optional of product instance
     */
    @Override
    public Optional<Product> findBy(String productName) {
        return productRepositoryImpl.findBy(productName);
    }

    /**
     * Find product by Id
     *
     * @param productId product Id
     * @return Optional of product instance
     */
    @Override
    public Optional<Product> findBy(int productId) {
        return productRepositoryImpl.findBy(productId);
    }

    /**
     * Find all products
     *
     * @return Optional of List of products
     */
    @Override
    public Optional<List> findAll() {
        return productRepositoryImpl.findAll();
    }

    /**
     * Update product status
     *
     * @param productId      product Id
     * @param isDiscontinued product status
     * @return
     */
    @Override
    public Optional<Product> update(int productId, boolean isDiscontinued) {
        var productOptional = productRepositoryImpl.findBy(productId);

        if (productOptional.isPresent()) {
            var product = productOptional.get();
            product.setDiscontinued(isDiscontinued);
            productRepositoryImpl.update(product);
            return productRepositoryImpl.findBy(productId);
        } else {
            log.error("Product is not present to be updated");
        }
        return Optional.empty();
    }

    /**
     * Delete product from database
     *
     * @param productId product Id
     * @return
     */
    @Override
    public boolean delete(int productId) {
        var productOptional = productRepositoryImpl.findBy(productId);

        if (productOptional.isPresent()) {
            var product = productOptional.get();
            productRepositoryImpl.delete(product);
            return productRepositoryImpl.findBy(productId).isEmpty();
        } else {
            log.error("Product is not present to be deleted");
        }
        return false;
    }

    /**
     * Close ProductRepository
     */
    @Override
    public void close() {
        productRepositoryImpl.close();
    }
}
