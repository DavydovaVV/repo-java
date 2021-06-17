package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.converter.ProductToDtoConverter;
import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.dto.ProductDto;
import com.epam.rd.davydova.assignment.service.impl.OrderServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.ProductServiceImpl;
import com.epam.rd.davydova.assignment.service.impl.SupplierServiceImpl;
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
@WebMvcTest(ProductResourceImpl.class)
@ContextConfiguration(classes = {ProductResourceImpl.class, ProductServiceImpl.class, ProductToDtoConverter.class})
class ProductResourceImplTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ConversionService conversionService;

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
    private ProductServiceImpl productServiceImpl;
    @MockBean
    private SupplierServiceImpl supplierServiceImpl;
    @MockBean
    private OrderServiceImpl orderServiceImpl;

    @Test
    void addNewProductTest() {
        var productDto = conversionService.convert(product, ProductDto.class);
        when(productServiceImpl.add(product)).thenReturn(product);
        when(supplierServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(supplier));
        var mapper = new ObjectMapper();
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/product/add")
                    .content(mapper.writeValueAsString(productDto))
                    .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void getProductByIdTest() {
        when(productServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(product));
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/product/get?id=1"))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void getAllProductsTest() {
        var productList = new ArrayList<Product>();
        productList.add(product);
        when(productServiceImpl.findAll()).thenReturn(productList);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/product/get"))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void updatePresentProductTest() {
        product.getOrderList().add(order);
        var productDto = conversionService.convert(product, ProductDto.class);
        var mapper = new ObjectMapper();
        when(productServiceImpl.update(product)).thenReturn(product);
        when(productServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(product));
        when(supplierServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(supplier));
        when(orderServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(order));
        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/product/update")
                    .content(mapper.writeValueAsString(productDto)).contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void deletePresentProductTest() {
        when(productServiceImpl.delete(1L)).thenReturn(true);
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete?id=1"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void deleteAbsentProductTest() {
        when(productServiceImpl.delete(1L)).thenReturn(false);
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete?id=1"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }
}