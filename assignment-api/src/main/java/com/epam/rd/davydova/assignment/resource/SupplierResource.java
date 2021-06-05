package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.dto.SupplierDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is an interface to interact with database from api layer
 */
@RequestMapping("/supplier")
public interface SupplierResource {

    /**
     * Add supplier to database
     *
     * @param stringSupplier string representation of Supplier object
     * @return string result of method
     */
    @PostMapping
    SupplierDto addSupplier(@RequestBody String stringSupplier);

    /**
     * Get supplier from database
     *
     * @param id supplier Id
     * @return string result of method
     */
    @GetMapping("/{id}")
    List<SupplierDto> getSupplier(@PathVariable(required = false) Long id);

    /**
     * Update supplier in database
     *
     * @param stringSupplier string representation of Supplier object
     * @return string result of method
     */
    @PutMapping
    SupplierDto updateSupplier(@RequestBody String stringSupplier);

    /**
     * Delete supplier from database
     *
     * @param id supplier Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/{id}")
    HttpStatus deleteSupplier(@PathVariable long id);
}
