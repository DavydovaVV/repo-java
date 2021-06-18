package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.TestEntityFactory;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    @MockBean
    private CustomerServiceImpl customerServiceImpl;

    @Test
    public void addNewCustomerTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var customerDto = conversionService.convert(customer, CustomerDto.class);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(customerDto);
        when(customerServiceImpl.add(customer)).thenReturn(customer);
        when(customerServiceImpl.findBy(customer.getCustomerName()))
                .thenReturn(Optional.of(customer));
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/add")
                .contentType("application/json").content(json))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var customerDto = conversionService.convert(customer, CustomerDto.class);
        var mapper = new ObjectMapper();
        var customerDtoList = new ArrayList<CustomerDto>();
        customerDtoList.add(customerDto);
        var json = mapper.writeValueAsString(customerDtoList);
        when(customerServiceImpl.findBy(1L))
                .thenReturn(Optional.ofNullable(customer));
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/get?id=1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var customerDto = conversionService.convert(customer, CustomerDto.class);
        var mapper = new ObjectMapper();
        var customerList = new ArrayList<Customer>();
        var customerDtoList = new ArrayList<CustomerDto>();
        customerList.add(customer);
        customerDtoList.add(customerDto);
        var json = mapper.writeValueAsString(customerDtoList);
        when(customerServiceImpl.findAll()).thenReturn(customerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/get"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updatePresentCustomerTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var customerDto = conversionService.convert(customer, CustomerDto.class);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(customerDto);
        when(customerServiceImpl.findBy(1L))
                .thenReturn(Optional.ofNullable(customer));
        when(customerServiceImpl.update(customer)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.put("/customer/update")
                .contentType("application/json")
                .content(json))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deletePresentCustomer() throws Exception {
        when(customerServiceImpl.delete(1L)).thenReturn(true);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(HttpStatus.OK);
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete?id=1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deleteAbsentCustomer() throws Exception {
        when(customerServiceImpl.delete(1L)).thenReturn(false);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete?id=1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}