package com.epam.rd.davydova.assignment.service.impl;

import com.epam.rd.davydova.assignment.dto.ProductDto;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.repository.ProductRepository;
import com.epam.rd.davydova.assignment.repository.SupplierRepository;
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
    private final SupplierRepository supplierRepository;

    /**
     * Add product to database
     *
     * @param productDto DTO for Product object
     * @return Optional of Product object
     */
    @Override
    public Product add(ProductDto productDto) {
        var product = new Product();

        var supplierId = productDto.getSupplierId();

        if (supplierRepository.existsById(supplierId)) {
            var supplier = supplierRepository.getById(supplierId);

            product.setProductName(productDto.getProductName())
                    .setSupplier(supplier)
                    .setUnitPrice(productDto.getUnitPrice())
                    .setDiscontinued(productDto.isDiscontinued());

            supplier.getProductList().add(product);

            return productRepository.save(product);
        }

        return null;
    }

    /**
     * Find product by name
     *
     * @param productName product name
     * @return Optional of product instance
     */
    @Override
    public Optional<Product> findBy(String productName) {
        return productRepository.findByName(productName);
    }

    /**
     * Find product by Id
     *
     * @param productId product Id
     * @return Optional of product instance
     */
    @Override
    public Optional<Product> findBy(long productId) {
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
     * @param productDto DTO for Product object
     * @return Optional of Product object
     */
    @Override
    public Product update(ProductDto productDto) {
        var productOptional = productRepository.findById(productDto.getProductId());
        var supplierId = productDto.getSupplierId();

        if (productOptional.isPresent()) {
            if (supplierRepository.existsById(supplierId)) {
                var product = productOptional.get();
                var supplier = supplierRepository.getById(supplierId);

                product.setProductName(productDto.getProductName())
                        .setSupplier(supplier)
                        .setUnitPrice(productDto.getUnitPrice())
                        .setDiscontinued(productDto.isDiscontinued());

                return productRepository.save(product);
            }

        } else {
            log.error("Product Id is not found. Product is not updated");
        }

        return null;
    }

    /**
     * Delete product from database
     *
     * @param productId product Id
     * @return status of deletion
     */
    @Override
    public boolean delete(long productId) {
        var productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            var product = productOptional.get();

            productRepository.delete(product);

            if (productRepository.existsById(productId)) {
                return true;
            }
        } else {
            log.error("Product Id is not found. Product is not deleted");
        }
        return false;
    }
}
