package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is an interface to interact with database from api layer
 */
@RestController
@RequestMapping("/order")
public interface OrderResource {

    /**
     * Add order to database
     *
     * @param orderDto DTO of Order object
     * @return string result of method
     */
    @PostMapping
    OrderDto addOrder (@RequestBody OrderDto orderDto);

    /**
     * Get order from database
     *
     * @param id order Id
     * @return string result of method
     */
    @GetMapping("/{id}")
    List<OrderDto> getOrder(@PathVariable(required = false) long id);

    /**
     * Update order in database
     *
     * @param orderDto DTO of Order object
     * @return string result of method
     */
    @PutMapping
    OrderDto updateOrder(@RequestBody OrderDto orderDto);

    /**
     * Delete order from database
     *
     * @param id order Id
     * @return result of deletion
     */
    @DeleteMapping(value = "/{id}")
    HttpStatus deleteOrder(@PathVariable long id);
}
