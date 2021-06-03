package com.epam.rd.davydova.assignment.domain.service;

import com.epam.rd.davydova.assignment.domain.model.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

/**
 * This is a class for operations with Supplier entity and database
 */
@Slf4j
@Data
@NoArgsConstructor
@Service
public class SupplierService {
    private EntityManager entityManager;

    @Autowired
    public SupplierService (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /**
     * Add supplier to database
     *
     * @param companyName supplier instance
     */
    public void add(String companyName, String phone) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var supplier = new Supplier();
            supplier.setCompanyName(companyName);
            supplier.setPhone(phone);
            entityManager.persist(supplier);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Supplier is not added. Exception is: ", e);
        }
    }

    /**
     * Find supplier by their name
     *
     * @param companyName company name
     * @return supplier instance
     */
    public Optional<Supplier> findBy(String companyName) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var supplier = (Supplier) entityManager
                    .createNamedQuery(Supplier.FIND_SUPPLIER_BY_NAME)
                    .setParameter(1, companyName)
                    .getSingleResult();
            transaction.commit();
            var foundSupplier = Optional.ofNullable(supplier);
            if (foundSupplier.isPresent()) {
                log.info("Supplier is found");
                return foundSupplier;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Supplier is not found. Exception is: ", e);
        }
        return Optional.empty();
    }

    /**
     * Find supplier by Id
     *
     * @param supplierId supplier Id
     * @return Optional of supplier instance
     */
    public Optional<Supplier> findBy(int supplierId) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var supplier = entityManager.find(Supplier.class, supplierId);
            transaction.commit();
            var foundSupplier = Optional.ofNullable(supplier);
            if (foundSupplier.isPresent()) {
                log.info("Supplier is found");
                return foundSupplier;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Supplier is not found. Exception is: ", e);
        }
        return Optional.empty();
    }

    /**
     * Find all suppliers
     *
     * @return Optional of List of suppliers
     */
    public Optional<List> findAll() {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var supplierList = entityManager.createNamedQuery(Supplier.FIND_ALL_SUPPLIERS).getResultList();
            transaction.commit();
            var foundSupplierList = Optional.ofNullable(supplierList);
            if (foundSupplierList.isPresent()) {
                log.info("List of suppliers is found");
                return foundSupplierList;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("List of suppliers is not found. Exception is: ", e);
        }
        return Optional.empty();
    }

    /**
     * Update supplier's company name and phone number
     *
     * @param supplierId supplier Id
     * @param phone      phone number
     */
    public void update(int supplierId, String phone) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var supplier = entityManager.find(Supplier.class, supplierId);
            supplier.setPhone(phone);
            entityManager.merge(supplier);
            transaction.commit();
            log.info("Supplier is updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Supplier is not updated. Exception is: ", e);
        }
    }

    /**
     * Delete supplier from database
     *
     * @param supplierId supplier Id
     */
    public void delete(int supplierId) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.find(Supplier.class, supplierId));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Supplier is not deleted. Exception is: ", e);
        }
    }
}
