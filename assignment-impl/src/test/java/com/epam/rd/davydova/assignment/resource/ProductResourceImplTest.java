package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.TestEntityFactory;
import com.epam.rd.davydova.assignment.converter.ProductToDtoConverter;
import com.epam.rd.davydova.assignment.domain.entity.Product;
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

    @MockBean
    private ProductServiceImpl productServiceImpl;
    @MockBean
    private SupplierServiceImpl supplierServiceImpl;
    @MockBean
    private OrderServiceImpl orderServiceImpl;

    @Test
    public void addNewProductTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var product = testEntityFactory.createTestProduct();
        var productDto = conversionService.convert(product, ProductDto.class);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(productDto);
        when(productServiceImpl.add(product)).thenReturn(product);
        when(productServiceImpl.findBy(product.getProductName())).thenReturn(Optional.of(product));
        when(supplierServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(supplier));
        mockMvc.perform(MockMvcRequestBuilders.post("/product/add")
                .content(json).contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getProductByIdTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var product = testEntityFactory.createTestProduct();
        var productDto = conversionService.convert(product, ProductDto.class);
        var productDtoList = new ArrayList<ProductDto>();
        productDtoList.add(productDto);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(productDtoList);
        when(productServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(product));
        mockMvc.perform(MockMvcRequestBuilders.get("/product/get?id=1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getAllProductsTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var product = testEntityFactory.createTestProduct();
        var productDto = conversionService.convert(product, ProductDto.class);
        var productDtoList = new ArrayList<ProductDto>();
        productDtoList.add(productDto);
        var productList = new ArrayList<Product>();
        productList.add(product);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(productDtoList);
        when(productServiceImpl.findAll()).thenReturn(productList);
        mockMvc.perform(MockMvcRequestBuilders.get("/product/get"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updatePresentProductTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var product = testEntityFactory.createTestProduct();
        var order = testEntityFactory.createTestOrder();
        product.getOrderList().add(order);
        var productDto = conversionService.convert(product, ProductDto.class);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(productDto);
        when(productServiceImpl.update(product)).thenReturn(product);
        when(productServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(product));
        when(supplierServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(supplier));
        when(orderServiceImpl.findBy(1L)).thenReturn(Optional.ofNullable(order));
        mockMvc.perform(MockMvcRequestBuilders.put("/product/update")
                .content(json).contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deletePresentProductTest() throws Exception {
        when(productServiceImpl.delete(1L)).thenReturn(true);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(HttpStatus.OK);
        mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete?id=1"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deleteAbsentProductTest() throws Exception {
        when(productServiceImpl.delete(1L)).thenReturn(false);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete?id=1"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}