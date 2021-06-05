package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.converter.dto.CustomerToDtoConverter;
import com.epam.rd.davydova.assignment.converter.entity.StringToCustomerConverter;
import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.dto.CustomerDto;
import com.epam.rd.davydova.assignment.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class of CustomerServlet
 */
@Slf4j
@RestController
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomerResourceImpl implements CustomerResource {
    private final CustomerServiceImpl customerService;

    /**
     * Add customer to database
     *
     * @param stringCustomer string representation of Customer object
     * @return DTO of Customer object
     */
    @Override
    public CustomerDto addCustomer(String stringCustomer) {
        var converter = new StringToCustomerConverter();

        var customerDto = converter.convert(stringCustomer);

        var customer = customerService.add(customerDto);

        customerDto.setCustomerId(customer.getCustomerId());

        log.info("addCustomer() - {}", customer);

        return customerDto;
    }

    /**
     * Get customer(s) from database
     *
     * @param id customer Id
     * @return List of CustomerDto objects
     */
    @Override
    public List<CustomerDto> getCustomer(Long id) {
        List<CustomerDto> customerDtoList = new ArrayList<>();

        var converter = new CustomerToDtoConverter();

        if (id != null) {
            var customerOptional = customerService.findBy(id);

            if (customerOptional.isPresent()) {
                var customer = customerOptional.get();

                var customerDto = converter.convert(customer);

                customerDtoList.add(customerDto);
            }
        } else {
            var customerList = customerService.findAll();

            for (Customer customer : customerList) {
                customerDtoList.add(converter.convert(customer));
            }
        }

        log.info("getCustomer() - {}", customerDtoList);

        return customerDtoList;
    }

    /**
     * Update customer in database
     *
     * @param stringCustomer string representation of Customer object
     * @return DTO of Customer object
     */
    @Override
    public CustomerDto updateCustomer(String stringCustomer) {
        var converter = new StringToCustomerConverter();

        var customerDto = converter.convert(stringCustomer);

        var customer = customerService.update(customerDto);

        log.info("updateCustomer() - {}", customer);

        return customerDto;
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
