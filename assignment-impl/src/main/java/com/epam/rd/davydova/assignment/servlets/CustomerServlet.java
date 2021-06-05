package com.epam.rd.davydova.assignment.servlets;

import com.epam.rd.davydova.assignment.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * This is a class of CustomerServlet
 */
@RequiredArgsConstructor
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private final CustomerServiceImpl customerServiceImpl;

    /**
     * Post customer to database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var customerName = jsonRequest.optString("customer_name");
        var phone = jsonRequest.optString("customer_phone");

        customerServiceImpl.add(customerName, phone);
    }

    /**
     * Get customer from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        var customerId = request.getParameter("customer_id");

        setTypeAndEncoding(response);

        if (customerId != null) {
            var customer = customerServiceImpl.findBy(Integer.parseInt(customerId));
            if (customer.isPresent()) {
                var jsonCustomer = new JSONObject(customer.get());
                try (var printWriter = response.getWriter()) {
                    printWriter.print(jsonCustomer);
                } catch (IOException e) {
                    log("Exception is: ", e);
                }
            } else {
                log("Customer is not present");
            }
        } else {
            var allCustomers = customerServiceImpl.findAll();
            if (allCustomers.isPresent()) {
                var jsonCustomerArray = new JSONArray(allCustomers.get());
                try (var printWriter = response.getWriter()) {
                    printWriter.print(jsonCustomerArray);
                } catch (IOException e) {
                    log("Exception is: ", e);
                }
            } else {
                log("Customer list is not present");
            }
        }
    }

    /**
     * Update customer in database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var customerId = jsonRequest.optString("customer_id");
        var phone = jsonRequest.optString("customer_phone");

        customerServiceImpl.update(Integer.parseInt(customerId), phone);
    }

    /**
     * Delete customer from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var customerId = jsonRequest.optString("customer_id");

        customerServiceImpl.delete(Integer.parseInt(customerId));
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
            var result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            var jsonObject = new JSONObject(result);

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
