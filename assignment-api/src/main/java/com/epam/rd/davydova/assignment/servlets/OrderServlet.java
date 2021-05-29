package com.epam.rd.davydova.assignment.servlets;

import com.epam.rd.davydova.assignment.domain.service.OrderService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * This is a class of OrderServlet
 */
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private OrderService orderService = new OrderService();

    /**
     * Post order to database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var productId = Integer.parseInt(jsonRequest.optString("product_id"));
        var customerId = Integer.parseInt(jsonRequest.optString("customer_id"));
        var orderNumber = jsonRequest.optString("order_number");
        var numberOfProducts = Integer.parseInt(jsonRequest.optString("number_of_products"));

        orderService.add(productId, customerId, orderNumber, numberOfProducts);
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

        setTypeAndEncoding(response);

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
        var jsonRequest = readRequest(request).get();
        var orderId = Integer.parseInt(jsonRequest.optString("order_id"));
        var productId = Integer.parseInt(jsonRequest.optString("product_id"));
        var orderNumber = jsonRequest.optString("order_number");
        var numberOfProducts = Integer.parseInt(jsonRequest.optString("number_of_products"));

        orderService.update(orderId, orderNumber, productId, numberOfProducts);
    }

    /**
     * Delete order from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var orderId = jsonRequest.optString("order_id");

        orderService.delete(Integer.parseInt(orderId));
    }

    /**
     * Read result from InputStream
     *
     * @param request request
     * @return Optional of resulted string
     */
    private Optional<JSONObject> readRequest(HttpServletRequest request) {
        try {
            var inputStream = request.getInputStream();
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(result);

            return Optional.of(jsonObject);
        } catch (IOException e) {
            log("Exception is: ", e);
        }
        return Optional.empty();
    }

    /**
     * Set type and encoding of response
     *
     * @param response response
     */
    private void setTypeAndEncoding(HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
    }
}
