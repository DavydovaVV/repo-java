package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.dto.ProductDto;
import com.epam.rd.davydova.assignment.service.impl.ProductServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.SupplierServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class of CustomerServlet
 */
@Slf4j
@RequiredArgsConstructor
public class ProductResourceImpl implements ProductResource {
    private final ProductServiceImpl productService;
    private final SupplierServiceImpl supplierService;
    private final ConversionService conversionService;

    /**
     * Add product to database
     *
     * @param productDto DTO of Product object
     * @return DTO of Product object
     */
    @Override
    public ProductDto addProduct(ProductDto productDto) {
        var supplierId = productDto.getSupplierId();
        var supplierOptional = supplierService.findBy(supplierId);
        if (supplierOptional.isPresent()) {
            var supplier = supplierOptional.get();
            var product = new Product()
                    .setProductName(productDto.getProductName())
                    .setSupplier(supplier)
                    .setUnitPrice(productDto.getUnitPrice())
                    .setDiscontinued(productDto.isDiscontinued());
            supplier.getProductList().add(product);
            var addedProduct = productService.add(product);
            productDto.setProductId(addedProduct.getProductId());
            log.info("addProduct() - {}", product);
            return productDto;
        } else {
            log.error("Supplier is not found. Product is not added");
        }
        return null;
    }

    /**
     * Get product(s) from database
     *
     * @param id product Id
     * @return List of ProductDto objects
     */
    @Override
    public List<ProductDto> getProduct(long id) {
        List<ProductDto> productDtoList = new ArrayList<>();
        if (id != 0) {
            var productOptional = productService.findBy(id);
            if (productOptional.isPresent()) {
                var product = productOptional.get();
                var productDto = conversionService.convert(product, ProductDto.class);
                productDtoList.add(productDto);
                log.info("getProduct() - {}", product);
            } else {
                var productList = productService.findAll();
                for (Product product : productList) {
                    productDtoList.add(conversionService.convert(product, ProductDto.class));
                }
                log.info("getProduct() - {}", productList);
            }
        }
        return productDtoList;
    }

    /**
     * Update product in database
     *
     * @param productDto DTO of Product object
     * @return string result of method
     */
    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        var productOptional = productService.findBy(productDto.getProductId());
        var supplierId = productDto.getSupplierId();
        if (productOptional.isPresent()) {
            var supplierOptional = supplierService.findBy(supplierId);
            if (supplierOptional.isPresent()) {
                var product = productOptional.get();
                var supplier = supplierOptional.get();
                product.setProductName(productDto.getProductName())
                        .setSupplier(supplier)
                        .setUnitPrice(productDto.getUnitPrice())
                        .setDiscontinued(productDto.isDiscontinued());
                var updatedProduct = productService.update(product);
                productDto.setProductId(updatedProduct.getProductId());
                log.info("updateProduct() - {}", product);
                return productDto;
            } else {
                log.error("Supplier is not found. Product is not updated");
            }
        } else {
            log.error("Product is not found. Product is not updated");
        }
        return productDto;
    }

    /**
     * Delete product from database
     *
     * @param id product Id
     * @return status of deletion
     */
    @Override
    public HttpStatus deleteProduct(long id) {
        var isRemoved = productService.delete(id);
        if (!isRemoved) {
            return HttpStatus.NOT_FOUND;
        }
        log.info("deleteSupplier() - {}", id);
        return HttpStatus.OK;
    }
}
