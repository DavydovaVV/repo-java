package com.epam.rd.davydova.assignment.converter;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;

/**
 * Converter from Product to ProductDto
 */
@Slf4j
public class ProductToDtoConverter implements Converter<Product, ProductDto> {

    /**
     * Convert product to productDto
     *
     * @param product Product object
     * @return productDto
     */
    @Override
    public ProductDto convert(Product product) {
        var orderList = product.getOrderList();
        var orderIdList = new ArrayList<Long>();
        for(Order order : orderList) {
            orderIdList.add(order.getOrderId());
        }
        var productDto = new ProductDto()
                .setProductId(product.getProductId())
                .setProductName(product.getProductName())
                .setOrderIdList(orderIdList)
                .setSupplierId(product.getSupplier().getSupplierId())
                .setUnitPrice(product.getUnitPrice())
                .setDiscontinued(product.isDiscontinued());
        log.info("convert() - convert from '{}' to {}", product, productDto);
        return productDto;
    }
}
