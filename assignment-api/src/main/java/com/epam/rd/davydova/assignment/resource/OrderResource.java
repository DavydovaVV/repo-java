package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is an interface to interact with database from api layer
 */
@RequestMapping("/order")
public interface OrderResource {

    /**
     * Add order to database
     *
     * @param stringOrder string representation of Order object
     * @return string result of method
     */
    @PostMapping
    OrderDto addOrder (@RequestBody String stringOrder);

    /**
     * Get order from database
     *
     * @param id order Id
     * @return string result of method
     */
    @GetMapping("/{id}")
    List<OrderDto> getOrder(@PathVariable(required = false) Long id);

    /**
     * Update order in database
     *
     * @param stringOrder string representation of Order object
     * @return string result of method
     */
    @PutMapping
    OrderDto updateOrder(@RequestBody String stringOrder);

    /**
     * Delete order from database
     *
     * @param id order Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/{id}")
    HttpStatus deleteOrder(@PathVariable long id);
}
