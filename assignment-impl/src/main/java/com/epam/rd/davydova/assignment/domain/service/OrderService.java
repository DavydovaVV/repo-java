package com.epam.rd.davydova.assignment.domain.service;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

/**
 * This is a class for operations with Order entity and database
 */
@Slf4j
@Data
public class OrderService {
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("PurchasePU");

    /**
     * Add order to database
     *
     * @param productId        product Id
     * @param customerId       customer Id
     * @param numberOfProducts quantity of product unit in order
     */
    public void add(int productId, int customerId, int numberOfProducts) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = new Order();
            var product = entityManager.find(Product.class, productId);
            var pricePerUnit = product.getUnitPrice().doubleValue();
            order.setOrderDate(new Date());
            order.setCustomer(entityManager.find(Customer.class, customerId));
            order.setTotalAmount(BigDecimal.valueOf(pricePerUnit * numberOfProducts));
            order.addToList(product);
            product.addToList(order);
            entityManager.persist(order);
            transaction.commit();
            log.info("Order is added");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Order is not added. Exception is: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Find order by number
     *
     * @param orderNumber order number
     * @return Optional of order instance
     */
    public Optional<Order> findBy(String orderNumber) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Order order = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            order = (Order) entityManager
                    .createNamedQuery(Order.FIND_ORDER_BY_NUMBER)
                    .setParameter(1, orderNumber)
                    .getSingleResult();
            transaction.commit();
            var foundSupplier = Optional.ofNullable(order);
            if (foundSupplier.isPresent()) {
                log.info("Order is found");
                return foundSupplier;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Order is not found. Exception is: ", e);
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    /**
     * Update order
     *
     * @param orderId          order Id
     * @param orderNumber      order Number
     * @param productId        product Id
     * @param numberOfProducts quantity of product unit in order
     */
    public void update(int orderId, String orderNumber, int productId, int numberOfProducts) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = entityManager.find(Order.class, orderId);
            var product = entityManager.find(Product.class, productId);
            var pricePerUnit = product.getUnitPrice().doubleValue();
            order.setOrderDate(new Date());
            order.setOrderNumber(orderNumber);
            var singleResult = (BigDecimal) entityManager
                    .createNamedQuery(Order.FIND_TOTAL_AMOUNT_PER_ORDER)
                    .setParameter(1, orderId)
                    .getSingleResult();
            order.setTotalAmount(BigDecimal.valueOf(pricePerUnit * numberOfProducts).add(singleResult));
            entityManager.merge(order);
            transaction.commit();
            log.info("Order is updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Order is not updated. Exception is: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete order from database
     *
     * @param orderId order Id
     */
    public void delete(int orderId) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.find(Order.class, orderId));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Order is not deleted. Exception is: ", e);
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
