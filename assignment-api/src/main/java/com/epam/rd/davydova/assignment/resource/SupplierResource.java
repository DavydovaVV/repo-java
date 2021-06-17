package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.dto.SupplierDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is an interface to interact with database from api layer
 */
@RequestMapping(value = "/supplier")
public interface SupplierResource {

    /**
     * Add supplier to database
     *
     * @param supplierDto DTO of Supplier object
     * @return string result of method
     */
    @PostMapping(value = "/add")
    @ResponseBody
    SupplierDto addSupplier(@RequestBody SupplierDto supplierDto);

    /**
     * Get supplier from database
     *
     * @param id supplier Id
     * @return string result of method
     */
    @GetMapping(value = "/get")
    @ResponseBody
    List<SupplierDto> getSupplier(@RequestParam(value = "id", required = false) Long id);

    /**
     * Update supplier in database
     *
     * @param supplierDto DTO of Supplier object
     * @return string result of method
     */
    @PutMapping(value = "/update")
    @ResponseBody
    SupplierDto updateSupplier(@RequestBody SupplierDto supplierDto);

    /**
     * Delete supplier from database
     *
     * @param id supplier Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/delete")
    HttpStatus deleteSupplier(@RequestParam(value = "id") Long id);
}
