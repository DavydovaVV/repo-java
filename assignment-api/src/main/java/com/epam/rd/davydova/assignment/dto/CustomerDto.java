package com.epam.rd.davydova.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

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

    private String customerName;

    private String phone;
}