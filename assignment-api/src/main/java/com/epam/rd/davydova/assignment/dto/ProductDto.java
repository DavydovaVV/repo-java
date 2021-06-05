package com.epam.rd.davydova.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
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
    private long productId;

    private List<Long> orderIdList;

    @NotNull
    private String productName;

    @NotNull
    private long supplierId;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private boolean isDiscontinued;
}