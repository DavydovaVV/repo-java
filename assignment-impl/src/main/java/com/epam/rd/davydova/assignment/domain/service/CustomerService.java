package com.epam.rd.davydova.assignment.domain.service;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

/**
 * This is a class for operations with Customer entity and database
 */
@Slf4j
@Data
public class CustomerService {
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("PurchasePU");

    /**
     * Add customer to database
     *
     * @param customerName customer instance
     */
    public void add(String customerName, String phone) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var customer = new Customer();
            customer.setCustomerName(customerName);
            customer.setPhone(phone);
            entityManager.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Customer is not added. Exception is: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Find customer by their name
     *
     * @param customerName customer's name
     * @return customer instance
     */
    public Optional<Customer> findBy(String customerName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var customer = (Customer) entityManager
                    .createNamedQuery(Customer.FIND_CUSTOMER_BY_NAME)
                    .setParameter(1, customerName)
                    .getSingleResult();
            transaction.commit();
            var foundCustomer = Optional.ofNullable(customer);
            if (foundCustomer.isPresent()) {
                log.info("Customer is found");
                return foundCustomer;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Customer is not found. Exception is: ", e);
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    /**
     * Find customer by Id
     *
     * @param customerId customer Id
     * @return Optional of customer instance
     */
    public Optional<Customer> findBy(int customerId) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var customer = entityManager.find(Customer.class, customerId);
            transaction.commit();
            var foundCustomer = Optional.ofNullable(customer);
            if (foundCustomer.isPresent()) {
                log.info("Customer is found");
                return foundCustomer;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Customer is not found. Exception is: ", e);
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    /**
     * Find all customers
     *
     * @return Optional of List of customers
     */
    public Optional<List> findAll() {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var customerList = entityManager.createNamedQuery(Customer.FIND_ALL_CUSTOMERS).getResultList();
            transaction.commit();
            var foundCustomerList = Optional.ofNullable(customerList);
            if (foundCustomerList.isPresent()) {
                log.info("List of customers is found");
                return foundCustomerList;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("List of customers is not found. Exception is: ", e);
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    /**
     * Update customer's name and phone number
     *
     * @param customerId customer Id
     * @param phone      phone number
     */
    public void update(int customerId, String phone) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var customer = entityManager.find(Customer.class, customerId);
            customer.setPhone(phone);
            entityManager.merge(customer);
            transaction.commit();
            log.info("Customer is updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Customer is not updated. Exception is: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete customer from database
     *
     * @param customerId customer Id
     */
    public void delete(int customerId) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.find(Customer.class, customerId));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Customer is not deleted. Exception is: ", e);
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
