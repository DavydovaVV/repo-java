package com.epam.rd.davydova.assignment.servlets;

import com.epam.rd.davydova.assignment.domain.service.OrderService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

/**
 * This is a class of OrderServlet
 */
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private OrderService orderService = new OrderService();

    /**
     * Default constructor
     */
    public OrderServlet() {
        super();
    }

    /**
     * Post order to database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        var productId = Integer.parseInt(request.getParameter("product_id"));
        var customerId = Integer.parseInt(request.getParameter("customer_id"));
        var orderNumber = request.getParameter("order_number");
        var numberOfProducts = Integer.parseInt(request.getParameter("number_of_products"));

        orderService.add(productId, customerId, orderNumber, numberOfProducts);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var order = orderService.findBy(orderNumber);
        if (order.isPresent()) {
            var jsonOrder = new JSONObject(order.get());
            try (var printWriter = response.getWriter()) {
                printWriter.print(jsonOrder);
                log("Order is added");
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Order is not present");
        }
    }

    /**
     * Get order by Id or all order list from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        var orderId = request.getParameter("order_id");
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);

        if (orderId != null) {
            var order = orderService.findBy(Integer.parseInt(orderId));
            if (order.isPresent()) {
                var jsonOrder = new JSONObject(order.get());
                try (var printWriter = response.getWriter()) {
                    printWriter.print(jsonOrder);
                } catch (IOException e) {
                    log("Exception is: ", e);
                }
            } else {
                log("Order is not present");
            }
        } else {
            var allOrders = orderService.findAll();
            if (allOrders.isPresent()) {
                var jsonOrderArray = new JSONArray(allOrders.get());
                try (var printWriter = response.getWriter()) {
                    printWriter.print(jsonOrderArray);
                } catch (IOException e) {
                    log("Exception is: ", e);
                }
            } else {
                log("Order list is not present");
            }
        }
    }

    /**
     * Update order in database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        var orderId = Integer.parseInt(request.getParameter("order_id"));
        var productId = Integer.parseInt(request.getParameter("product_id"));
        var orderNumber = request.getParameter("order_number");
        var numberOfProducts = Integer.parseInt(request.getParameter("number_of_products"));

        orderService.update(orderId, orderNumber, productId, numberOfProducts);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var order = orderService.findBy(orderId);
        if (order.isPresent()) {
            try (var printWriter = response.getWriter()) {
                printWriter.print(SC_OK);
                log("Order is updated");
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Order is not present");
        }
    }

    /**
     * Delete order from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        var orderId = request.getParameter("order_id");

        orderService.delete(Integer.parseInt(orderId));

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var order = orderService.findBy(orderId);
        if (order.isEmpty()) {
            try (var printWriter = response.getWriter()) {
                printWriter.print(SC_OK);
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Order is deleted");
        }
    }
}
