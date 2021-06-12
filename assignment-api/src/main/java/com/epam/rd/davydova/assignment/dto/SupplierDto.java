package com.epam.rd.davydova.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This is a DTO class for Supplier entity
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SupplierDto {
    private long supplierId;

    private List<Long> productIdList;

    @NotNull
    private String companyName;

    @NotNull
    private String phone;
}