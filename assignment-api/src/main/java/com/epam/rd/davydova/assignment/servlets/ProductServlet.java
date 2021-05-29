package com.epam.rd.davydova.assignment.servlets;

import com.epam.rd.davydova.assignment.domain.service.ProductService;
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
 * This is a class of ProductServlet
 */
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private ProductService productService = new ProductService();

    /**
     * Post product to database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var productName = jsonRequest.optString("product_name");
        var supplierId = Integer.parseInt(jsonRequest.optString("supplier_id"));
        var unitPrice = Double.parseDouble(jsonRequest.optString("unit_price"));

        productService.add(productName, supplierId, unitPrice);
    }

    /**
     * Get product by Id or all product list from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        var productId = request.getParameter("product_id");

        setTypeAndEncoding(response);

        if (productId != null) {
            var product = productService.findBy(Integer.parseInt(productId));
            if (product.isPresent()) {
                var jsonProduct = new JSONObject(product.get());
                try (var printWriter = response.getWriter()) {
                    printWriter.print(jsonProduct);
                } catch (IOException e) {
                    log("Exception is: ", e);
                }
            } else {
                log("Product is not present");
            }
        } else {
            var allProducts = productService.findAll();
            if (allProducts.isPresent()) {
                var jsonProductArray = new JSONArray(allProducts.get());
                try (var printWriter = response.getWriter()) {
                    printWriter.print(jsonProductArray);
                } catch (IOException e) {
                    log("Exception is: ", e);
                }
            } else {
                log("Product list is not present");
            }
        }
    }

    /**
     * Update product in database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var productId = jsonRequest.optString("product_id");
        var isDiscontinued = Boolean.parseBoolean(jsonRequest.optString("is_discontinued"));

        productService.update(Integer.parseInt(productId), isDiscontinued);
    }

    /**
     * Delete product from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        var jsonRequest = readRequest(request).get();
        var productId = jsonRequest.optString("product_id");

        productService.delete(Integer.parseInt(productId));
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
