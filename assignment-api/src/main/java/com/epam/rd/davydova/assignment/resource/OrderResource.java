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
    @ResponseBody
    @PostMapping(value = "/add")
    OrderDto addOrder (@RequestBody OrderDto orderDto);

    /**
     * Get order from database
     *
     * @param id order Id
     * @return string result of method
     */
    @ResponseBody
    @GetMapping(value = "/get")
    List<OrderDto> getOrder(@RequestParam(value = "id", required = false) Long id);

    /**
     * Update order in database
     *
     * @param orderDto DTO of Order object
     * @return string result of method
     */
    @ResponseBody
    @PutMapping(value = "/update")
    OrderDto updateOrder(@RequestBody OrderDto orderDto);

    /**
     * Delete order from database
     *
     * @param id order Id
     * @return result of deletion
     */
    @ResponseBody
    @DeleteMapping(value = "/delete")
    HttpStatus deleteOrder(@RequestParam(value = "id") Long id);
}
