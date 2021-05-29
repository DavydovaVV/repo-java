package com.epam.rd.davydova.assignment.domain.service;

import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * This is a class for operations with Product entity and database
 */
@Slf4j
@Data
public class ProductService {
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("PurchasePU");

    /**
     * Add product to database
     *
     * @param productName product name
     * @param supplierId  supplier Id
     * @param unitPrice price per unit
     */
    public void add(String productName, int supplierId, double unitPrice) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var product = new Product();
            product.setProductName(productName);
            product.setSupplier(entityManager.find(Supplier.class, supplierId));
            product.setUnitPrice(BigDecimal.valueOf(unitPrice));
            product.setDiscontinued(false);
            entityManager.persist(product);
            transaction.commit();
            log.info("Product is added");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Product is not added. Exception is: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Find product by name
     *
     * @param productName product name
     * @return Optional of product instance
     */
    public Optional<Product> findBy(String productName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Product product = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            product = (Product) entityManager
                    .createNamedQuery(Product.FIND_PRODUCT_BY_NAME)
                    .setParameter(1, productName)
                    .getSingleResult();
            transaction.commit();
            var foundProduct = Optional.ofNullable(product);
            if (foundProduct.isPresent()) {
                log.info("Product is found");
                return foundProduct;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Product is not found. Exception is: ", e);
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    /**
     * Update product status
     *
     * @param productId      product Id
     * @param isDiscontinued product status
     */
    public void update(int productId, boolean isDiscontinued) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var product = entityManager.find(Product.class, productId);
            product.setDiscontinued(isDiscontinued);
            entityManager.merge(product);
            transaction.commit();
            log.info("Product is updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Product is not updated. Exception is: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete product from database
     *
     * @param productId product Id
     */
    public void delete(int productId) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.find(Product.class, productId));
            transaction.commit();
            log.info("Product is deleted");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Product is not deleted. Exception is: ", e);
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
