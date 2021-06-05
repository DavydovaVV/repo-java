package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.converter.dto.SupplierToDtoConverter;
import com.epam.rd.davydova.assignment.converter.entity.StringToSupplierConverter;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.dto.SupplierDto;
import com.epam.rd.davydova.assignment.service.impl.SupplierServiceImpl;
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
public class SupplierResourceImpl implements SupplierResource {
    private final SupplierServiceImpl supplierService;

    /**
     * Add supplier to database
     *
     * @param stringSupplier string representation of Supplier object
     * @return DTO of Supplier object
     */
    @Override
    public SupplierDto addSupplier(String stringSupplier) {
        var converter = new StringToSupplierConverter();

        var supplierDto = converter.convert(stringSupplier);

        var supplier = supplierService.add(supplierDto);

        supplierDto.setSupplierId(supplier.getSupplierId());

        log.info("addSupplier() - {}", supplier);

        return supplierDto;
    }

    /**
     * Get supplier(s) from database
     *
     * @param id supplier Id
     * @return List of SupplierDto objects
     */
    @Override
    public List<SupplierDto> getSupplier(Long id) {
        List<SupplierDto> supplierDtoList = new ArrayList<>();

        var converter = new SupplierToDtoConverter();

        if (id != null) {
            var supplierOptional = supplierService.findBy(id);

            if (supplierOptional.isPresent()) {
                var supplier = supplierOptional.get();

                var supplierDto = converter.convert(supplier);

                supplierDtoList.add(supplierDto);

                log.info("getSupplier() - {}", supplier);

            } else {
                var supplierList = supplierService.findAll();

                for (Supplier supplier : supplierList) {
                    supplierDtoList.add(converter.convert(supplier));
                }

                log.info("getSupplier() - {}", supplierList);
            }
        }
        return supplierDtoList;
    }

    /**
     * Update supplier in database
     *
     * @param stringSupplier string representation of Supplier object
     * @return DTO of Supplier object
     */
    @Override
    public SupplierDto updateSupplier(String stringSupplier) {
        var converter = new StringToSupplierConverter();

        var supplierDto = converter.convert(stringSupplier);

        var supplier = supplierService.update(supplierDto);

        log.info("updateSupplier() - {}", supplier);

        return supplierDto;
    }

    /**
     * Delete supplier from database
     *
     * @param id supplier Id
     * @return status of deletion
     */
    @Override
    public HttpStatus deleteSupplier(long id) {
        var isRemoved = supplierService.delete(id);

        if (!isRemoved) {
            return HttpStatus.NOT_FOUND;
        }

        log.info("deleteSupplier() - {}", id);

        return HttpStatus.OK;
    }
}
