package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.converter.CustomerToDtoConverter;
import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.dto.CustomerDto;
import com.epam.rd.davydova.assignment.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing class for CustomerResourceImpl
 */
@Slf4j
@WebMvcTest(CustomerResourceImpl.class)
@ContextConfiguration(classes = {CustomerServiceImpl.class, CustomerResourceImpl.class, CustomerToDtoConverter.class})
class CustomerResourceImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ConversionService conversionService;

    private Customer customer = new Customer()
            .setCustomerId(1L)
            .setCustomerName("Name")
            .setPhone("1111");

    @MockBean
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void addNewCustomerTest() {
        var customerDto = conversionService.convert(customer, CustomerDto.class);
        when(customerServiceImpl.add(customer)).thenReturn(customer);
        var mapper = new ObjectMapper();

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/customer/add")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(customerDto)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void getCustomerByIdTest() {
        when(customerServiceImpl.findBy(1L))
                .thenReturn(Optional.ofNullable(customer));
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/customer/get?id=1"))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void getAllCustomersTest() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerServiceImpl.findAll()).thenReturn(customerList);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/customer/get"))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void updatePresentCustomerTest() {
        var customerDto = conversionService.convert(customer, CustomerDto.class);
        when(customerServiceImpl.findBy(1L))
                .thenReturn(Optional.ofNullable(customer));
        when(customerServiceImpl.update(customer)).thenReturn(customer);
        var mapper = new ObjectMapper();

        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/customer/update")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(customerDto)))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void deletePresentCustomer() {
        when(customerServiceImpl.delete(1L)).thenReturn(true);
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete?id=1"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void deleteAbsentCustomer() {
        when(customerServiceImpl.delete(1L)).thenReturn(false);
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete?id=1"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }
}