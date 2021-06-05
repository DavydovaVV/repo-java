package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is an interface to interact with database from api layer
 */
@RequestMapping("/product")
public interface ProductResource {

    /**
     * Add product to database
     *
     * @param stringProduct string representation of Product object
     * @return string result of method
     */
    @PostMapping
    ProductDto addProduct(@RequestBody String stringProduct);

    /**
     * Get product from database
     *
     * @param id product Id
     * @return string result of method
     */
    @GetMapping("/{id}")
    List<ProductDto> getProduct(@PathVariable(required = false) Long id);

    /**
     * Update product in database
     *
     * @param stringProduct string representation of Product object
     * @return string result of method
     */
    @PutMapping
    ProductDto updateProduct(@RequestBody String stringProduct);

    /**
     * Delete product from database
     *
     * @param id product Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/{id}")
    HttpStatus deleteProduct(@PathVariable long id);
}
