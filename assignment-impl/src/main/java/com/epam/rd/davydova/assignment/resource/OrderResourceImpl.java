package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.converter.dto.OrderToDtoConverter;
import com.epam.rd.davydova.assignment.converter.entity.StringToOrderConverter;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.dto.OrderDto;
import com.epam.rd.davydova.assignment.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class of CustomerServlet
 */
@Slf4j
@RestController
@RestControllerAdvice
@RequiredArgsConstructor
public class OrderResourceImpl implements OrderResource {
    private final OrderServiceImpl orderService;

    /**
     * Add order to database
     *
     * @param stringOrder string representation of Order object
     * @return DTO of Order object
     */
    @Override
    public OrderDto addOrder(String stringOrder) {
        var converter = new StringToOrderConverter();

        var orderDto = converter.convert(stringOrder);

        var order = orderService.add(orderDto);

        orderDto.setOrderId(order.getOrderId());

        log.info("addOrder() - {}", order);

        return orderDto;
    }

    /**
     * Get order(s) from database
     *
     * @param id order Id
     * @return List of OrderDto objects
     */
    @Override
    public List<OrderDto> getOrder(Long id) {
        List<OrderDto> orderDtoList = new ArrayList<>();

        var converter = new OrderToDtoConverter();

        if (id != null) {
            var orderOptional = orderService.findBy(id);

            if (orderOptional.isPresent()) {
                var order = orderOptional.get();

                var orderDto = converter.convert(order);

                orderDtoList.add(orderDto);

                log.info("getOrder() - {}", order);
            } else {
                var orderList = orderService.findAll();

                for (Order order : orderList) {
                    orderDtoList.add(converter.convert(order));
                }

                log.info("getProduct() - {}", orderList);
            }
        }
        return orderDtoList;
    }

    /**
     * Update order to database
     *
     * @param stringOrder string representation of Order object
     * @return DTO of Order object
     */
    @Override
    public OrderDto updateOrder(String stringOrder) {
        var converter = new StringToOrderConverter();

        var orderDto = converter.convert(stringOrder);

        var order = orderService.add(orderDto);

        log.info("updateOrder() - {}", order);

        return orderDto;
    }

    /**
     * Delete order from database
     *
     * @param id order Id
     * @return status of deletion
     */
    @Override
    public HttpStatus deleteOrder(long id) {
        var isRemoved = orderService.delete(id);

        if (!isRemoved) {
            return HttpStatus.NOT_FOUND;
        }

        log.info("deleteSupplier() - {}", id);

        return HttpStatus.OK;
    }
}
