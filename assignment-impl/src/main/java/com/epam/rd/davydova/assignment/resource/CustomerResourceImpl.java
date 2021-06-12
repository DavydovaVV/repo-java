package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.dto.CustomerDto;
import com.epam.rd.davydova.assignment.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class of CustomerController
 */
@Slf4j
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
    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        var customer = new Customer();
        customer.setCustomerName(customerDto.getCustomerName())
                .setPhone(customerDto.getPhone());
        var addedCustomer = customerService.add(customer);
        customerDto.setCustomerId(addedCustomer.getCustomerId());
        log.info("addCustomer() - {}", customer);

        return conversionService.convert(customer, CustomerDto.class);
    }

    /**
     * Get customer(s) from database
     *
     * @param id customer Id
     * @return List of CustomerDto objects
     */
    @Override
    public List<CustomerDto> getCustomer(long id) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        if (id != 0) {
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
    @Override
    public HttpStatus deleteCustomer(long id) {
        var isRemoved = customerService.delete(id);
        if (!isRemoved) {
            return HttpStatus.NOT_FOUND;
        }
        log.info("deleteCustomer() - {}", id);
        return HttpStatus.OK;
    }
}
