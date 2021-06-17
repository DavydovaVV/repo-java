package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.converter.OrderToDtoConverter;
import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.dto.OrderDto;
import com.epam.rd.davydova.assignment.service.impl.CustomerServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.OrderServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.ProductServiceImpl;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing class for ProductResourceImpl
 */
@Slf4j
@WebMvcTest(OrderResourceImpl.class)
@ContextConfiguration(classes = {OrderResourceImpl.class, OrderServiceImpl.class, OrderToDtoConverter.class})
class OrderResourceImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ConversionService conversionService;

    private Customer customer = new Customer()
            .setCustomerId(1L)
            .setCustomerName("Name")
            .setPhone("1111");

    private Supplier supplier = new Supplier()
            .setSupplierId(1L)
            .setCompanyName("KiwiCo")
            .setPhone("2222");

    private Product product = new Product()
            .setProductId(1L)
            .setProductName("Kiwi")
            .setSupplier(supplier)
            .setUnitPrice(BigDecimal.valueOf(100));

    private Order order = new Order()
            .setOrderId(1L)
            .setOrderNumber("u001")
            .setOrderDate(new Date())
            .setCustomer(customer)
            .setProductList(List.of(product))
            .setTotalAmount(BigDecimal.valueOf(100));

    @MockBean
    private OrderServiceImpl orderServiceImpl;
    @MockBean
    private ProductServiceImpl productServiceImpl;
    @MockBean
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void addNewOrderTest() {
        var orderDto = conversionService.convert(order, OrderDto.class);
        var mapper = new ObjectMapper();
        when(productServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(product));
        when(customerServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(customer));
        when(orderServiceImpl.add(order)).thenReturn(order);
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/order/add")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(orderDto)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void getOrderByIdTest() {
        when(orderServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(order));
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/order/get?id=1"))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void getAllOrdersTest() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(orderServiceImpl.findAll()).thenReturn(orderList);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/order/get"))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void updatePresentOrderTest() {
        var orderDto = conversionService.convert(order, OrderDto.class);
        var mapper = new ObjectMapper();
        when(productServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(product));
        when(customerServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(customer));
        when(orderServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(order));
        when(orderServiceImpl.update(order)).thenReturn(order);
        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/order/update")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(orderDto)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void deletePresentOrderTest() {
        when(orderServiceImpl.delete(1L)).thenReturn(true);
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/order/delete?id=1"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void deleteAbsentOrderTest() {
        when(orderServiceImpl.delete(1L)).thenReturn(false);
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/order/delete?id=1"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }
}