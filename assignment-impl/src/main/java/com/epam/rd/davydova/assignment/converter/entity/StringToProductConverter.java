package com.epam.rd.davydova.assignment.converter.entity;

import com.epam.rd.davydova.assignment.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Converter from String to ProductDto
 */
@Slf4j
public class StringToProductConverter implements Converter<String, ProductDto> {

    /**
     * Convert string to productDto
     *
     * @param string received string with data
     * @return productDto
     */
    @Override
    public ProductDto convert(String string) {
        String[] strings = string.split(",");

        var orderStringIdList = new ArrayList<>(Arrays.asList(strings[1].split(",")));
        var orderIdList = new ArrayList<Long>();

        for(String productId : orderStringIdList) {
            orderIdList.add(Long.parseLong(productId));
        }

        var productDto = new ProductDto()
                .setProductName(strings[0])
                .setOrderIdList(orderIdList)
                .setSupplierId(Long.parseLong(strings[2]))
                .setUnitPrice(BigDecimal.valueOf(Integer.parseInt(strings[3])))
                .setDiscontinued(Boolean.parseBoolean(strings[4]));
        log.info("convert() - convert from '{}' to {}", string, productDto);
        return productDto;
    }
}
