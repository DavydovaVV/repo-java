package com.epam.rd.davydova.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * This is a DTO class for Product entity
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductDto {
    private Long productId;

    private List<Long> orderIdList;

    private String productName;

    private Long supplierId;

    private BigDecimal unitPrice;

    private boolean isDiscontinued;
}