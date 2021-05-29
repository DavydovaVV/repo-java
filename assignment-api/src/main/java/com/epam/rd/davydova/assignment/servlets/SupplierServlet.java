package com.epam.rd.davydova.assignment.servlets;

import com.epam.rd.davydova.assignment.domain.service.SupplierService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

/**
 * This is a class of SupplierServlet
 */
public class SupplierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private SupplierService supplierService = new SupplierService();

    /**
     * Default constructor
     */
    public SupplierServlet() {
        super();
    }

    /**
     * Post supplier to database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        var companyName = request.getParameter("company_name");
        var phone = request.getParameter("phone");
        supplierService.add(companyName, phone);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var supplier = supplierService.findBy(companyName);
        if (supplier.isPresent()) {
            var jsonSupplier = new JSONObject(supplier.get());
            try (var printWriter = response.getWriter()) {
                printWriter.print(jsonSupplier);
                log("Supplier is added");
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Supplier is not present");
        }
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
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);

        if (supplierId != null) {
            var supplier = supplierService.findBy(Integer.parseInt(supplierId));
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
            var allSuppliers = supplierService.findAll();
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
        var supplierId = request.getParameter("supplier_id");
        var phone = request.getParameter("phone");

        supplierService.update(Integer.parseInt(supplierId), phone);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var supplier = supplierService.findBy(supplierId);
        if (supplier.isPresent()) {
            try (var printWriter = response.getWriter()) {
                printWriter.print(SC_OK);
                log("Supplier is updated");
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Supplier is not present");
        }
    }

    /**
     * Delete supplier from database
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        var supplierId = request.getParameter("supplier_id");

        supplierService.delete(Integer.parseInt(supplierId));

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        var supplier = supplierService.findBy(supplierId);
        if (supplier.isEmpty()) {
            try (var printWriter = response.getWriter()) {
                printWriter.print(SC_OK);
            } catch (IOException e) {
                log("Exception is: ", e);
            }
        } else {
            log("Supplier is deleted");
        }
    }
}
