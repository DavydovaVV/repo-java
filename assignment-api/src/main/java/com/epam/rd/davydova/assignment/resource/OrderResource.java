package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is an interface to interact with database from api layer
 */
@RequestMapping(value = "/order")
public interface OrderResource {

    /**
     * Add order to database
     *
     * @param orderDto DTO of Order object
     * @return string result of method
     */
    @PostMapping
    @ResponseBody OrderDto addOrder (@RequestBody OrderDto orderDto);

    /**
     * Get order from database
     *
     * @param id order Id
     * @return string result of method
     */
    @GetMapping("/{id}")
    @ResponseBody List<OrderDto> getOrder(@PathVariable(value = "id", required = false) Long id);

    /**
     * Update order in database
     *
     * @param orderDto DTO of Order object
     * @return string result of method
     */
    @PutMapping
    @ResponseBody OrderDto updateOrder(@RequestBody OrderDto orderDto);

    /**
     * Delete order from database
     *
     * @param id order Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody HttpStatus deleteOrder(@PathVariable(value = "id") Long id);
}
