package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.dto.ProductDto;
import com.epam.rd.davydova.assignment.service.impl.OrderServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.ProductServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.SupplierServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class of CustomerServlet
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductResourceImpl implements ProductResource {
    private final ProductServiceImpl productService;
    private final SupplierServiceImpl supplierService;
    private final OrderServiceImpl orderService;
    private final ConversionService conversionService;

    /**
     * Add product to database
     *
     * @param productDto DTO of Product object
     * @return DTO of Product object
     */
    @Transactional
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
    @Transactional
    @Override
    public List<ProductDto> getProduct(Long id) {
        List<ProductDto> productDtoList = new ArrayList<>();
        if (id != null) {
            var productOptional = productService.findBy(id);
            if (productOptional.isPresent()) {
                var product = productOptional.get();
                var productDto = conversionService.convert(product, ProductDto.class);
                productDtoList.add(productDto);
                log.info("getProduct() - {}", product);
            }
        } else {
            var productList = productService.findAll();
            for (Product product : productList) {
                productDtoList.add(conversionService.convert(product, ProductDto.class));
            }
            log.info("getProduct() - {}", productList);
        }
        return productDtoList;
    }

    /**
     * Update product in database
     *
     * @param productDto DTO of Product object
     * @return string result of method
     */
    @Transactional
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
                var orderList = product.getOrderList();
                orderList.clear();
                var orderIdList = productDto.getOrderIdList();
                for(Long orderId : orderIdList) {
                    var orderOptional = orderService.findBy(orderId);
                    if (orderOptional.isPresent()) {
                        var order = orderOptional.get();
                        orderList.add(order);
                        var productList = order.getProductList();
                        if(!productList.contains(product)) {
                            productList.add(product);
                        }
                    }
                }
                var updatedProduct = productService.update(product);
                productDto = conversionService.convert(updatedProduct, ProductDto.class);
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
    public HttpStatus deleteProduct(Long id) {
        var isRemoved = productService.delete(id);
        if (!isRemoved) {
            return HttpStatus.NOT_FOUND;
        }
        log.info("deleteProduct() - {}", id);
        return HttpStatus.OK;
    }
}
