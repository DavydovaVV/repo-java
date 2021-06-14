package com.epam.rd.davydova.assignment.resource;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.dto.SupplierDto;
import com.epam.rd.davydova.assignment.service.impl.SupplierServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class of CustomerServlet
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SupplierResourceImpl implements SupplierResource {
    private final SupplierServiceImpl supplierService;
    private final ConversionService conversionService;

    /**
     * Add supplier to database
     *
     * @param supplierDto DTO of Supplier object
     * @return DTO of Supplier object
     */
    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) {
        var supplier = new Supplier()
                .setCompanyName(supplierDto.getCompanyName())
                .setPhone(supplierDto.getPhone());
        var addedSupplier = supplierService.add(supplier);
        supplierDto.setSupplierId(addedSupplier.getSupplierId());
        log.info("addSupplier() - {}", supplier);
        return supplierDto;
    }

    /**
     * Get supplier(s) from database
     *
     * @param id supplier Id
     * @return List of SupplierDto objects
     */
    @Transactional
    @Override
    public List<SupplierDto> getSupplier(Long id) {
        List<SupplierDto> supplierDtoList = new ArrayList<>();
        if (id != null) {
            var supplierOptional = supplierService.findBy(id);
            if (supplierOptional.isPresent()) {
                var supplier = supplierOptional.get();
                var supplierDto = conversionService.convert(supplier, SupplierDto.class);
                supplierDtoList.add(supplierDto);
                log.info("getSupplier() - {}", supplier);
            }
        } else {
            var supplierList = supplierService.findAll();
            for (Supplier supplier : supplierList) {
                supplierDtoList.add(conversionService.convert(supplier, SupplierDto.class));
            }
            log.info("getSupplier() - {}", supplierList);
        }
        return supplierDtoList;
    }

    /**
     * Update supplier in database
     *
     * @param supplierDto DTO of Supplier object
     * @return string result of method
     */
    @Override
    public SupplierDto updateSupplier(SupplierDto supplierDto) {
        var supplierOptional = supplierService.findBy(supplierDto.getSupplierId());
        if (supplierOptional.isPresent()) {
            var supplier = supplierOptional.get();
            supplier.setCompanyName(supplierDto.getCompanyName())
                    .setPhone(supplierDto.getPhone());
            log.info("updateSupplier() - {}", supplier);
            supplierService.update(supplier);
            return supplierDto;
        } else {
            log.error("Supplier is not found. Supplier is not updated");
        }
        return null;
    }

    /**
     * Delete supplier from database
     *
     * @param id supplier Id
     * @return status of deletion
     */
    @Override
    public HttpStatus deleteSupplier(Long id) {
        var isRemoved = supplierService.delete(id);
        if (!isRemoved) {
            return HttpStatus.NOT_FOUND;
        }
        log.info("deleteSupplier() - {}", id);
        return HttpStatus.OK;
    }
}
