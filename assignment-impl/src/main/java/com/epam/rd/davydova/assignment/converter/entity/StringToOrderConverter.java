package com.epam.rd.davydova.assignment.converter.entity;

import com.epam.rd.davydova.assignment.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Converter from String to OrderDto
 */
@Slf4j
public class StringToOrderConverter implements Converter<String, OrderDto> {

    /**
     * Convert string to orderDto
     *
     * @param string received string with data
     * @return orderDto
     */
    @Override
    public OrderDto convert(String string) {
        String[] strings = string.split(",");

        var productStringIdList = new ArrayList<>(Arrays.asList(strings[1].split(",")));
        var productIdList = new ArrayList<Long>();

        for(String productId : productStringIdList) {
            productIdList.add(Long.parseLong(productId));
        }

        var orderDto = new OrderDto()
                .setOrderNumber(strings[0])
                .setProductIdList(productIdList)
                .setCustomerId(Integer.parseInt(strings[2]));
        log.info("convert() - convert from '{}' to {}", string, orderDto);
        return orderDto;
    }
}
