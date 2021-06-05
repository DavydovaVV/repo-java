package com.epam.rd.davydova.assignment.repository.impl;

import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
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
@Profile("!local")
@Component
public class ProductRepositoryImpl implements ProductRepository {
    private final EntityManager entityManager;

    /**
     * Save to database
     *
     * @param product Product object
     */
    @Override
    public void save(Product product) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
            log.info("Product is added");
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
     * @param productName product name
     * @return Optional of Product object
     */
    @Override
    public Optional<Product> findBy(String productName) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var product = (Product) entityManager
                    .createNamedQuery(Product.FIND_PRODUCT_BY_NAME)
                    .setParameter(1, productName)
                    .getSingleResult();
            transaction.commit();
            var foundProduct = Optional.ofNullable(product);
            if (foundProduct.isPresent()) {
                log.info("Product is found");
                return foundProduct;
            }
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Product with such a name is not found");
        }
        return Optional.empty();
    }

    /**
     * Find object in database by Id
     *
     * @param productId product Id
     * @return Optional of Product object
     */
    @Override
    public Optional<Product> findBy(int productId) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var product = entityManager.find(Product.class, productId);
            transaction.commit();
            var foundProduct = Optional.ofNullable(product);
            if (foundProduct.isPresent()) {
                log.info("Product is found");
                return foundProduct;
            }
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Product with such an Id is not found");
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
            var productList = entityManager.createNamedQuery(Product.FIND_ALL_PRODUCTS).getResultList();
            transaction.commit();
            var foundProductList = Optional.ofNullable(productList);
            if (foundProductList.isPresent()) {
                log.info("List of products is found");
                return foundProductList;
            }
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("List of products is not found");
        }
        return Optional.empty();
    }

    /**
     * Update object in database
     *
     * @param product Product object
     */
    @Override
    public void update(Product product) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(product);
            transaction.commit();
            log.info("Product is updated");
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
     * @param product Product object
     */
    @Override
    public void delete(Product product) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(product);
            transaction.commit();
            log.info("Product is deleted");
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
