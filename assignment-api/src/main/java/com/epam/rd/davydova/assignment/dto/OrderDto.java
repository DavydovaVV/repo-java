package com.epam.rd.davydova.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * This is a DTO class for Order entity
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderDto {
    private long orderId;

    private List<Long> productIdList;

    private String orderNumber;

    private long customerId;

    private Date orderDate;

    private BigDecimal totalAmount;
}