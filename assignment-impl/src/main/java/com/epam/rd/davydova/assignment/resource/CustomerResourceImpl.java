package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.annotation.Logging;
import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.dto.CustomerDto;
import com.epam.rd.davydova.assignment.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class of CustomerController
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerResourceImpl implements CustomerResource {
    private final CustomerServiceImpl customerService;
    private final ConversionService conversionService;

    /**
     * Add customer to database
     *
     * @param customerDto DTO of Customer object
     * @return DTO of Customer object
     */
    @Logging
    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        var customer = new Customer();
        customer.setCustomerName(customerDto.getCustomerName())
                .setPhone(customerDto.getPhone());
        customerService.add(customer);
        var customerOptional = customerService.findBy(customer.getCustomerName());
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
            customerDto = conversionService.convert(customer, CustomerDto.class);
            log.info("addCustomer() - {}", customer);
            return customerDto;
        } else {
            log.error("Customer is not added");
        }
        return null;
    }

    /**
     * Get customer(s) from database
     *
     * @param id customer Id
     * @return List of CustomerDto objects
     */
    @Logging
    @Transactional
    @Override
    public List<CustomerDto> getCustomer(Long id) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        if (id != null) {
            var customerOptional = customerService.findBy(id);
            if (customerOptional.isPresent()) {
                var customer = customerOptional.get();
                var customerDto = conversionService.convert(customer, CustomerDto.class);
                customerDtoList.add(customerDto);
                log.info("getCustomer() - {}", customer);
            }
        } else {
            var customerList = customerService.findAll();
            for (Customer customer : customerList) {
                customerDtoList.add(conversionService.convert(customer, CustomerDto.class));
            }
            log.info("getCustomer() - {}", customerList);
        }
        return customerDtoList;
    }

    /**
     * Update customer in database
     *
     * @param customerDto DTO of Customer object
     * @return string result of method
     */
    @Logging
    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        var customerOptional = customerService.findBy(customerDto.getCustomerId());
        if (customerOptional.isPresent()) {
            var customer = customerOptional.get();
            customer.setCustomerName(customerDto.getCustomerName())
                    .setPhone(customerDto.getPhone());
            customerService.update(customer);
            log.info("updateCustomer() - {}", customer);
            return customerDto;
        } else {
            log.error("Customer Id is not found. Customer is not updated");
        }
        return null;
    }

    /**
     * Delete customer from database
     *
     * @param id customer Id
     * @return HttpStatus
     */
    @Logging
    @Override
    public HttpStatus deleteCustomer(Long id) {
        var isRemoved = customerService.delete(id);
        if (!isRemoved) {
            return HttpStatus.NOT_FOUND;
        }
        log.info("deleteCustomer() - {}", id);
        return HttpStatus.OK;
    }
}
