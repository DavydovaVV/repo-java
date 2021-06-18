package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is an interface to interact with database from api layer
 */
@RestController
@RequestMapping(value = "/product")
public interface ProductResource {

    /**
     * Add product to database
     *
     * @param productDto DTO of Product object
     * @return string result of method
     */
    @ResponseBody
    @PostMapping(value = "/add")
    ProductDto addProduct(@RequestBody ProductDto productDto);

    /**
     * Get product from database
     *
     * @param id product Id
     * @return string result of method
     */
    @ResponseBody
    @GetMapping(value = "/get")
    List<ProductDto> getProduct(@RequestParam(value = "id", required = false) Long id);

    /**
     * Update product in database
     *
     * @param productDto DTO of Product object
     * @return string result of method
     */
    @ResponseBody
    @PutMapping(value = "/update")
    ProductDto updateProduct(@RequestBody ProductDto productDto);

    /**
     * Delete product from database
     *
     * @param id product Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/delete")
    HttpStatus deleteProduct(@RequestParam(value = "id") Long id);
}
