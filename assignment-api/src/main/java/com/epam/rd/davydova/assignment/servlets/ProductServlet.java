package com.epam.rd.davydova.assignment.servlets;

import com.epam.rd.davydova.assignment.domain.service.ProductService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

/**
 * This is a class of ProductServlet
 */
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private ProductService productService = new ProductService();

    /**
     * Default constructor
     */
    public ProductServlet() {
        super();
    }

    /**
     * Post product to database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        var productName = request.getParameter("product_name");
        var supplierId = Integer.parseInt(request.getParameter("supplier_id"));
        var unitPrice = Double.parseDouble(request.getParameter("unit_price"));
        productService.add(productName, supplierId, unitPrice);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var product = productService.findBy(productName);
        if (product.isPresent()) {
            var jsonProduct = new JSONObject(product.get());
            try (var printWriter = response.getWriter()) {
                printWriter.print(jsonProduct);
                log("Product is added");
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Product is not present");
        }
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
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);

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
        var productId = request.getParameter("product_id");
        var isDiscontinued = Boolean.parseBoolean(request.getParameter("is_discontinued"));

        productService.update(Integer.parseInt(productId), isDiscontinued);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var product = productService.findBy(productId);
        if (product.isPresent()) {
            try (var printWriter = response.getWriter()) {
                printWriter.print(SC_OK);
                log("Product is updated");
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Product is not present");
        }
    }

    /**
     * Delete product from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        var productId = request.getParameter("product_id");

        productService.delete(Integer.parseInt(productId));

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var product = productService.findBy(productId);
        if (product.isEmpty()) {
            try (var printWriter = response.getWriter()) {
                printWriter.print(SC_OK);
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Product is deleted");
        }
    }
}
