package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.TestEntityFactory;
import com.epam.rd.davydova.assignment.converter.SupplierToDtoConverter;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.dto.SupplierDto;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing class for SupplierResourceImpl
 */
@Slf4j
@WebMvcTest(SupplierResourceImpl.class)
@ContextConfiguration(classes = {SupplierServiceImpl.class, SupplierResourceImpl.class, SupplierToDtoConverter.class})
class SupplierResourceImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ConversionService conversionService;

    private SupplierDto supplierDto;

    @MockBean
    private SupplierServiceImpl supplierServiceImpl;

    @Test
    public void addNewSupplierTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var supplierDto = conversionService.convert(supplier, SupplierDto.class);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(supplierDto);
        when(supplierServiceImpl.add(supplier)).thenReturn(supplier);
        when(supplierServiceImpl.findBy(supplier.getCompanyName())).thenReturn(Optional.of(supplier));
        mockMvc.perform(MockMvcRequestBuilders.post("/supplier/add")
                .contentType("application/json").content(json))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getSupplierByIdTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var supplierDto = conversionService.convert(supplier, SupplierDto.class);
        var supplierDtoList = new ArrayList<SupplierDto>();
        supplierDtoList.add(supplierDto);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(supplierDtoList);
        when(supplierServiceImpl.findBy(1L))
                .thenReturn(Optional.ofNullable(supplier));
        mockMvc.perform(MockMvcRequestBuilders.get("/supplier/get?id=1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getAllSuppliersTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var supplierDto = conversionService.convert(supplier, SupplierDto.class);
        var supplierDtoList = new ArrayList<SupplierDto>();
        supplierDtoList.add(supplierDto);
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(supplier);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(supplierDtoList);
        supplierDto = conversionService.convert(supplierList, SupplierDto.class);
        when(supplierServiceImpl.findAll()).thenReturn(supplierList);
        mockMvc.perform(MockMvcRequestBuilders.get("/supplier/get"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updatePresentSupplierTest() throws Exception {
        var testEntityFactory = new TestEntityFactory();
        var supplier = testEntityFactory.createTestSupplier();
        var supplierDto = conversionService.convert(supplier, SupplierDto.class);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(supplierDto);
        when(supplierServiceImpl.findBy(1L))
                .thenReturn(Optional.ofNullable(supplier));
        when(supplierServiceImpl.update(supplier)).thenReturn(supplier);
        mockMvc.perform(MockMvcRequestBuilders.put("/supplier/update")
                .contentType("application/json").content(json))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deletePresentSupplier() throws Exception {
        when(supplierServiceImpl.delete(1L)).thenReturn(true);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(HttpStatus.OK);
        mockMvc.perform(MockMvcRequestBuilders.delete("/supplier/delete?id=1"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deleteAbsentSupplier() throws Exception {
        when(supplierServiceImpl.delete(1L)).thenReturn(false);
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.delete("/supplier/delete?id=1"))
                .andExpect(content().json(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}