package com.epam.rd.davydova.assignment.resource;

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

    private Supplier supplier = new Supplier()
            .setSupplierId(1L)
            .setCompanyName("KiwiCo")
            .setPhone("2222");


    @MockBean
    private SupplierServiceImpl supplierServiceImpl;

    @Test
    void addNewSupplierTest() {
        supplierDto = conversionService.convert(supplier, SupplierDto.class);
        when(supplierServiceImpl.add(supplier)).thenReturn(supplier);
        var mapper = new ObjectMapper();

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/supplier/add")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(supplierDto)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void getSupplierByIdTest() {
        when(supplierServiceImpl.findBy(1L))
                .thenReturn(Optional.ofNullable(supplier));
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/supplier/get?id=1"))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void getAllSuppliersTest() {
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(supplier);
        supplierDto = conversionService.convert(supplierList, SupplierDto.class);
        when(supplierServiceImpl.findAll()).thenReturn(supplierList);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/supplier/get"))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void updatePresentSupplierTest() {
        supplierDto = conversionService.convert(supplier, SupplierDto.class);
        when(supplierServiceImpl.findBy(1L))
                .thenReturn(Optional.ofNullable(supplier));
        when(supplierServiceImpl.update(supplier)).thenReturn(supplier);
        var mapper = new ObjectMapper();

        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/supplier/update")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(supplierDto)))
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void deletePresentSupplier() {
        when(supplierServiceImpl.delete(1L)).thenReturn(true);
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/supplier/delete?id=1"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }

    @Test
    void deleteAbsentSupplier() {
        when(supplierServiceImpl.delete(1L)).thenReturn(false);
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/supplier/delete?id=1"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            log.error("Exception is: ", e);
        }
    }
}