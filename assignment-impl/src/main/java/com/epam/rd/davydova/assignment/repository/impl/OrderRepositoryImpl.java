package com.epam.rd.davydova.assignment.repository.impl;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.repository.OrderRepository;
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
public class OrderRepositoryImpl implements OrderRepository {
    private final EntityManager entityManager;

    /**
     * Save to database
     *
     * @param order Order object
     */
    @Override
    public void save(Order order) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(order);
            transaction.commit();
            log.info("Order is added");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is: ", e);
        }
    }

    /**
     * Find object in database by number
     *
     * @param orderNumber order number
     * @return Optional of Order object
     */
    @Override
    public Optional<Order> findBy(String orderNumber) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = (Order) entityManager
                    .createNamedQuery(Order.FIND_ORDER_BY_NUMBER)
                    .setParameter(1, orderNumber)
                    .getSingleResult();
            transaction.commit();
            var foundOrder = Optional.ofNullable(order);
            if (foundOrder.isPresent()) {
                log.info("Order is found");
                return foundOrder;
            }
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Order with such a number is not found");
        }
        return Optional.empty();
    }

    /**
     * Find object in database by Id
     *
     * @param orderId order Id
     * @return Optional of Order object
     */
    @Override
    public Optional<Order> findBy(int orderId) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = entityManager.find(Order.class, orderId);
            transaction.commit();
            var foundOrder = Optional.ofNullable(order);
            if (foundOrder.isPresent()) {
                log.info("Order is found");
                return foundOrder;
            }
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Order with such an Id is not found");
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
            var orderList = entityManager.createNamedQuery(Order.FIND_ALL_ORDERS).getResultList();
            transaction.commit();
            var foundOrderList = Optional.ofNullable(orderList);
            if (foundOrderList.isPresent()) {
                log.info("List of orders is found");
                return foundOrderList;
            }
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("List of orders is not found");
        }
        return Optional.empty();
    }

    /**
     * Update object in database
     *
     * @param order Order object
     */
    @Override
    public void update(Order order) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(order);
            transaction.commit();
            log.info("Order is updated");
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
     * @param order Order object
     */
    @Override
    public void delete(Order order) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(order);
            transaction.commit();
            log.info("Order is deleted");
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
