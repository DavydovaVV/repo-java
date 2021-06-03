package com.epam.rd.davydova.assignment.domain.stub;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * This is a class that defines stubbed Supplier
 */
@Slf4j
@Getter
@Component
@PropertySource("classpath:properties/stub_supplier.properties")
public class SupplierStub {
    @Value("${supplier_id}")
    private int supplierId;

    @Value("${company_name}")
    private String companyName;

    @Value("${supplier_phone}")
    private String phone;
}