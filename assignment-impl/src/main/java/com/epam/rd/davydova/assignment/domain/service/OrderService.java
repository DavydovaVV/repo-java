package com.epam.rd.davydova.assignment.domain.service;

import com.epam.rd.davydova.assignment.domain.model.Customer;
import com.epam.rd.davydova.assignment.domain.model.Order;
import com.epam.rd.davydova.assignment.domain.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * This is a class for operations with Order entity and database
 */
@Slf4j
@Data
@NoArgsConstructor
@Service
public class OrderService {
    private EntityManager entityManager;

    @Autowired
    public OrderService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Add order to database
     *
     * @param productId        product Id
     * @param customerId       customer Id
     * @param orderNumber      order number
     * @param numberOfProducts number of products
     */
    public void add(int productId, int customerId, String orderNumber, int numberOfProducts) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = new Order();
            var product = entityManager.find(Product.class, productId);
            var pricePerUnit = product.getUnitPrice().doubleValue();
            order.setOrderDate(new Date());
            order.setCustomer(entityManager.find(Customer.class, customerId));
            order.setOrderNumber(orderNumber);
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
        }
    }

    /**
     * Find order by number
     *
     * @param orderNumber order number
     * @return Optional of order instance
     */
    public Optional<Order> findBy(String orderNumber) {
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
        }
        return Optional.empty();
    }

    /**
     * Find order by Id
     *
     * @param orderId order Id
     * @return Optional of order instance
     */
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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Order is not found. Exception is: ", e);
        }
        return Optional.empty();
    }

    /**
     * Find all orders
     *
     * @return Optional of List of orders
     */
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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("List of orders is not found. Exception is: ", e);
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
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = entityManager.find(Order.class, orderId);
            var product = entityManager.find(Product.class, productId);
            var pricePerUnit = product.getUnitPrice().doubleValue();
            order.setOrderDate(new Date());
            order.setOrderNumber(orderNumber);
            var currentTotalAmount = (BigDecimal) entityManager
                    .createNamedQuery(Order.FIND_TOTAL_AMOUNT_PER_ORDER)
                    .setParameter(1, orderId)
                    .getSingleResult();
            order.setTotalAmount(BigDecimal.valueOf(pricePerUnit * numberOfProducts)
                    .add(currentTotalAmount));

            if (!order.getProductList().contains(product)) {
                order.addToList(product);
                product.addToList(order);
            }

            entityManager.merge(order);
            transaction.commit();
            log.info("Order is updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Order is not updated. Exception is: ", e);
        }
    }

    /**
     * Delete order from database
     *
     * @param orderId order Id
     */
    public void delete(int orderId) {
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
        }
    }
}
