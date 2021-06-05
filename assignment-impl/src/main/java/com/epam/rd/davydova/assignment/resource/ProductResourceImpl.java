package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.converter.dto.ProductToDtoConverter;
import com.epam.rd.davydova.assignment.converter.entity.StringToProductConverter;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.dto.ProductDto;
import com.epam.rd.davydova.assignment.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class of CustomerServlet
 */
@Slf4j
@RestController
@RestControllerAdvice
@RequiredArgsConstructor
public class ProductResourceImpl implements ProductResource {
    private final ProductServiceImpl productService;

    /**
     * Add product to database
     *
     * @param stringProduct string representation of Product object
     * @return DTO of Product object
     */
    @Override
    public ProductDto addProduct(String stringProduct) {
        var converter = new StringToProductConverter();

        var productDto = converter.convert(stringProduct);

        var product = productService.add(productDto);

        productDto.setProductId(product.getProductId());

        log.info("addProduct() - {}", product);

        return productDto;
    }

    /**
     * Get product(s) from database
     *
     * @param id product Id
     * @return List of ProductDto objects
     */
    @Override
    public List<ProductDto> getProduct(Long id) {
        List<ProductDto> productDtoList = new ArrayList<>();

        var converter = new ProductToDtoConverter();

        if (id != null) {
            var productOptional = productService.findBy(id);

            if (productOptional.isPresent()) {
                var product = productOptional.get();

                var productDto = converter.convert(product);

                productDtoList.add(productDto);

                log.info("getProduct() - {}", product);
            } else {
                var productList = productService.findAll();

                for (Product product : productList) {
                    productDtoList.add(converter.convert(product));
                }

                log.info("getProduct() - {}", productList);
            }
        }
        return productDtoList;
    }

    /**
     * Update product in database
     *
     * @param stringProduct string representation of Product object
     * @return DTO of Product object
     */
    @Override
    public ProductDto updateProduct(String stringProduct) {
        var converter = new StringToProductConverter();

        var productDto = converter.convert(stringProduct);

        var product = productService.update(productDto);

        log.info("updateProduct() - {}", product);

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
