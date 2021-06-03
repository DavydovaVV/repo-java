package com.epam.rd.davydova.assignment.repository.impl;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import com.epam.rd.davydova.assignment.repository.interfaces.ISupplierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

/**
 * This is a class for crud-operations with database
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SupplierRepository implements ISupplierRepository {
    private final EntityManager entityManager;

    /**
     * Save to database
     *
     * @param supplier Supplier object
     */
    @Override
    public void save(Supplier supplier) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(supplier);
            transaction.commit();
            log.info("Supplier is added");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is: ", e);
        }
    }

    /**
     * Find object in database by name
     *
     * @param companyName company name
     * @return Optional of Supplier object
     */
    @Override
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
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Supplier with such a name is not found");
        }
        return Optional.empty();
    }

    /**
     * Find object in database by Id
     *
     * @param supplierId supplier Id
     * @return Optional of Supplier object
     */
    @Override
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
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Supplier with such an Id is not found");
        }
        return Optional.empty();
    }

    /**
     * Find list of objects in database
     *
     * @return Optional of List
     */
    @Override
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
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("List of suppliers is not found");
        }
        return Optional.empty();
    }

    /**
     * Update object in database
     *
     * @param supplier Supplier object
     */
    @Override
    public void update(Supplier supplier) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(supplier);
            transaction.commit();
            log.info("Supplier is updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is: ", e);
        }
    }

    /**
     * Delete object from database
     *
     * @param supplier Supplier object
     */
    @Override
    public void delete(Supplier supplier) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(supplier);
            transaction.commit();
            log.info("Supplier is deleted");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is: ", e);
        }
    }

    /**
     * Close EntityManages session
     */
    @Override
    public void close() {
        entityManager.close();
    }
}
