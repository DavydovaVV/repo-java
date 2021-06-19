package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.TestEntityFactory;
import com.epam.rd.davydova.assignment.converter.OrderToDtoConverter;
import com.epam.rd.davydova.assignment.domain.entity.Order;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    @MockBean
    private OrderServiceImpl orderServiceImpl;
    @MockBean
    private ProductServiceImpl productServiceImpl;
    @MockBean
    private CustomerServiceImpl customerServiceImpl;

    @Test
    public void addNewOrderTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var product = testEntityFactory.createTestProduct();
        var order = testEntityFactory.createTestOrder();
        var orderDto = conversionService.convert(order, OrderDto.class);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(orderDto);
        when(productServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(product));
        when(customerServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(customer));
        when(orderServiceImpl.findBy(orderDto.getOrderNumber())).thenReturn(Optional.ofNullable(order));
        when(orderServiceImpl.add(order)).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders.post("/order/add")
                .contentType("application/json").content(json))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getOrderByIdTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var order = testEntityFactory.createTestOrder();
        var orderDto = conversionService.convert(order, OrderDto.class);
        var mapper = new ObjectMapper();
        var orderDtoList = new ArrayList<OrderDto>();
        orderDtoList.add(orderDto);
        var json = mapper.writeValueAsString(orderDtoList);
        when(orderServiceImpl.findBy(1L))
                .thenReturn(Optional.ofNullable(order));
        mockMvc.perform(MockMvcRequestBuilders.get("/order/get?id=1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getAllOrdersTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var order = testEntityFactory.createTestOrder();
        var orderDto = conversionService.convert(order, OrderDto.class);
        var mapper = new ObjectMapper();
        List<Order> orderList = new ArrayList<>();
        var orderDtoList = new ArrayList<OrderDto>();
        orderList.add(order);
        orderDtoList.add(orderDto);
        var json = mapper.writeValueAsString(orderDtoList);
        when(orderServiceImpl.findAll()).thenReturn(orderList);
        mockMvc.perform(MockMvcRequestBuilders.get("/order/get"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updatePresentOrderTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var customer = testEntityFactory.createTestCustomer();
        var product = testEntityFactory.createTestProduct();
        var order = testEntityFactory.createTestOrder();
        var expectedOrder = testEntityFactory.createTestOrder();
        var totalAmount = order.getTotalAmount();
        expectedOrder.setTotalAmount(totalAmount.add(totalAmount));
        var orderDto = conversionService.convert(order, OrderDto.class);
        var expectedOrderDto = conversionService.convert(expectedOrder, OrderDto.class);
        var mapper = new ObjectMapper();
        var jsonActual = mapper.writeValueAsString(orderDto);
        var jsonExpected = mapper.writeValueAsString(expectedOrderDto);
        when(productServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(product));
        when(customerServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(customer));
        when(orderServiceImpl.findBy(1L)).thenReturn(Optional.of(order));
        when(orderServiceImpl.update(order)).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders.put("/order/update")
                .contentType("application/json").content(jsonActual))
                .andExpect(content().json(jsonExpected))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deletePresentOrderTest() throws Exception {
        when(orderServiceImpl.delete(1L)).thenReturn(true);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(HttpStatus.OK);
        mockMvc.perform(MockMvcRequestBuilders.delete("/order/delete?id=1"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deleteAbsentOrderTest() throws Exception {
        when(orderServiceImpl.delete(1L)).thenReturn(false);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.delete("/order/delete?id=1"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}