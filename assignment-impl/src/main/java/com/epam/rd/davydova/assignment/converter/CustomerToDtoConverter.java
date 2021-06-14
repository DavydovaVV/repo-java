package com.epam.rd.davydova.assignment.converter;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;

/**
 * Converter from Customer to CustomerDto
 */
@Slf4j
public class CustomerToDtoConverter implements Converter<Customer, CustomerDto> {

    /**
     * Convert Customer to CustomerDto
     *
     * @param customer Customer object
     * @return Customer object
     */
    @Override
    public CustomerDto convert(Customer customer) {
        var orderList = customer.getOrderList();
        var orderIdList = new ArrayList<Long>();
        if (orderList != null) {
            for (Order order : orderList) {
                orderIdList.add(order.getOrderId());
            }
        }
        var customerDto = new CustomerDto()
                .setCustomerId(customer.getCustomerId())
                .setOrderIdList(orderIdList)
                .setCustomerName(customer.getCustomerName())
                .setPhone(customer.getPhone());
        log.info("convert() - convert from '{}' to {}", customer, customerDto);
        return customerDto;
    }
}
