package com.epam.rd.davydova.assignment.repository.impl;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.repository.interfaces.ICustomerRepository;
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
public class CustomerRepository implements ICustomerRepository {
    private final EntityManager entityManager;

    /**
     * Save to database
     *
     * @param customer Customer object
     */
    @Override
    public void save(Customer customer) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(customer);
            transaction.commit();
            log.info("Customer is added");
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
     * @param customerName customer name
     * @return Optional of Customer object
     */
    @Override
    public Optional<Customer> findBy(String customerName) {
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
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Customer with such a name is not found");
        }
        return Optional.empty();
    }

    /**
     * Find object in database by Id
     *
     * @param customerId customer Id
     * @return Optional of Customer object
     */
    @Override
    public Optional<Customer> findBy(int customerId) {
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
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Customer with such an Id is not found");
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
            var customerList = entityManager.createNamedQuery(Customer.FIND_ALL_CUSTOMERS).getResultList();
            transaction.commit();
            var foundCustomerList = Optional.ofNullable(customerList);
            if (foundCustomerList.isPresent()) {
                log.info("List of customers is found");
                return foundCustomerList;
            }
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("List of customers is not found");
        }
        return Optional.empty();
    }

    /**
     * Update object in database
     *
     * @param customer Customer object
     */
    @Override
    public void update(Customer customer) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(customer);
            transaction.commit();
            log.info("Customer is updated");
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
     * @param customer Customer object
     */
    @Override
    public void delete(Customer customer) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(customer);
            transaction.commit();
            log.info("Customer is deleted");
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
