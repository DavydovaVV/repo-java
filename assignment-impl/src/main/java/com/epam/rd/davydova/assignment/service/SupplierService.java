package com.epam.rd.davydova.assignment.service;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Optional;

/**
 * This is a class for operations with Supplier entity and database
 */
@Slf4j
public class SupplierService {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("PurchasePU");

    /**
     * Add customer to database
     *
     * @param companyName supplier instance
     */
    public void add(String companyName, String phone) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
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
        } finally {
            entityManager.close();
        }
    }

    /**
     * Find customer by their name
     *
     * @param companyName customer's name
     * @return supplier instance
     */
    public Optional<Supplier> findBy(String companyName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Supplier supplier = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            supplier = (Supplier) entityManager
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
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    /**
     * Update supplier's company name and phone number
     *
     * @param supplierId  supplier Id
     * @param phone       phone number
     */
    public void update(int supplierId, String phone) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
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
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete supplier from database
     *
     * @param supplierId customer Id
     */
    public void delete(int supplierId) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
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
        } finally {
            entityManager.close();
        }
    }

    /**
     * Close EntityManagerFactory
     */
    public void close() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
