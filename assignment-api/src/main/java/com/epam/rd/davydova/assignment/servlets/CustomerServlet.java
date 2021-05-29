package com.epam.rd.davydova.assignment.servlets;

import com.epam.rd.davydova.assignment.domain.service.CustomerService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

@Slf4j
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private CustomerService customerService = new CustomerService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String customerName = request.getParameter("customer_name");
        String phone = request.getParameter("phone");
        customerService.add(customerName, phone);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var customer = customerService.findBy(customerName);
        if (customer.isPresent()) {
            var jsonCustomer = new JSONObject(customer.get());
            try (var printWriter = response.getWriter()) {
                printWriter.print(jsonCustomer);
                log("Customer is added");
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Customer is not present");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        var customerId = request.getParameter("customer_id");
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);

        if (customerId != null) {
            var customer = customerService.findBy(Integer.parseInt(customerId));
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
            var allCustomers = customerService.findAll();
            if (allCustomers.isPresent()) {
                var jsonCustomerArray = new JSONArray(allCustomers.get());
                try (var printWriter = response.getWriter()) {
                    printWriter.print(jsonCustomerArray);
                } catch (IOException e) {
                    log("Exception is: ", e);
                }
            } else {
                log.error("Customer list is not present");
            }
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        var customerId = request.getParameter("customer_id");
        var phone = request.getParameter("phone");

        customerService.update(Integer.parseInt(customerId), phone);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var customer = customerService.findBy(customerId);
        if (customer.isPresent()) {
            var jsonCustomer = new JSONObject(customer.get());
            try (var printWriter = response.getWriter()) {
                printWriter.print(jsonCustomer);
                log("Customer is updated");
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Customer is not present");
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        var customerId = request.getParameter("customer_id");

        customerService.delete(Integer.parseInt(customerId));

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var customer = customerService.findBy(customerId);
        if (customer.isPresent()) {
            try (var printWriter = response.getWriter()) {
                printWriter.print("Customer is not deleted");
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Customer is deleted");
        }
    }
}
