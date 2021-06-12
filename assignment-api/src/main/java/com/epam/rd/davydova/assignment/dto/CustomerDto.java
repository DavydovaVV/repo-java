package com.epam.rd.davydova.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This is a DTO class for Customer entity
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CustomerDto {
    private long customerId;

    private List<Long> orderIdList;

    @NotNull
    private String customerName;

    @NotNull
    private String phone;
}