package com.epam.rd.davydova.assignment.servlets;

import com.epam.rd.davydova.assignment.service.impl.SupplierServiceImpl;
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
 * This is a class of SupplierServlet
 */
@RequiredArgsConstructor
public class SupplierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private final SupplierServiceImpl supplierServiceImpl;

    /**
     * Post supplier to database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var companyName = jsonRequest.optString("company_name");
        var phone = jsonRequest.optString("customer_phone");

        supplierServiceImpl.add(companyName, phone);
    }

    /**
     * Get supplier by Id or all suppliers from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        var supplierId = request.getParameter("supplier_id");

        setTypeAndEncoding(response);

        if (supplierId != null) {
            var supplier = supplierServiceImpl.findBy(Integer.parseInt(supplierId));
            if (supplier.isPresent()) {
                var jsonSupplier = new JSONObject(supplier.get());
                try (var printWriter = response.getWriter()) {
                    printWriter.print(jsonSupplier);
                } catch (IOException e) {
                    log("Exception is: ", e);
                }
            } else {
                log("Supplier is not present");
            }
        } else {
            var allSuppliers = supplierServiceImpl.findAll();
            if (allSuppliers.isPresent()) {
                var jsonSupplierArray = new JSONArray(allSuppliers.get());
                try (var printWriter = response.getWriter()) {
                    printWriter.print(jsonSupplierArray);
                } catch (IOException e) {
                    log("Exception is: ", e);
                }
            } else {
                log("Supplier list is not present");
            }
        }
    }

    /**
     * Update supplier in database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var supplierId = jsonRequest.optString("supplier_id");
        var phone = jsonRequest.optString("customer_phone");

        supplierServiceImpl.update(Integer.parseInt(supplierId), phone);
    }

    /**
     * Delete supplier from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var supplierId = jsonRequest.optString("supplier_id");

        supplierServiceImpl.delete(Integer.parseInt(supplierId));
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
