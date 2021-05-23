package com.epam.rd.davydova.assignment.domain.service;

import com.epam.rd.davydova.assignment.domain.entity.Customer;
import com.epam.rd.davydova.assignment.domain.entity.Order;
import com.epam.rd.davydova.assignment.domain.entity.Product;
import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;

/**
 * This is a class to make CRUD operations with entities
 */
@Slf4j
public class ExecutionService {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("PurchasePU");

    /**
     * Add customer to database
     *
     * @param customerName customer instance
     */
    public void addCustomer(String customerName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var customer = findCustomerBy(customerName);

            if (customer != null) {
                log.info("Customer has been already added");
            } else {
                customer = new Customer(customerName);
                entityManager.persist(customer);
                log.info("Customer has been added");
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Add supplier to database
     *
     * @param companyName supplier instance
     */
    public void addSupplier(String companyName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var supplier = findSupplierBy(companyName);

            if (supplier != null) {
                log.info("Supplier has been already added");
            } else {
                supplier = new Supplier(companyName);
                entityManager.persist(supplier);
                log.info("Supplier has been added");
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Add order to database
     *
     * @param customerName customer's name
     * @param productName  product name
     */
    public void addOrder(String customerName, String productName, double totalAmount) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = new Order();
            order.setOrderDate(new Date());
            var customer = findCustomerBy(customerName);

            if (customer == null) {
                log.info("Customer is not found. Customer has been added");
                addCustomer(customerName);
                customer = findCustomerBy(customerName);
            }
            order.setCustomer(customer);
            order.setTotalAmount(BigDecimal.valueOf(totalAmount));
            customer.getOrderSet().add(order);
            entityManager.persist(order);
            var product = findProductBy(productName);

            if (product == null) {
                log.error("Such product is not available");
                entityManager.close();
                return;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Add product to database
     *
     * @param productName product name
     * @param companyName company name
     * @param unitPrice   price per unit
     */
    public void addProduct(String productName, String companyName, double unitPrice) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var product = findProductBy(productName);

            if (product != null) {
                log.error("Product has been already added");
                entityManager.close();
                return;
            }
            product = new Product();
            product.setProductName(productName);
            product.setUnitPrice(BigDecimal.valueOf(unitPrice));
            product.setDiscontinued(false);
            var supplier = findSupplierBy(companyName);

            if (supplier == null) {
                log.error("Supplier is not available");
                entityManager.close();
                return;
            }
            product.setSupplier(supplier);
            supplier.getProductSet().add(product);
            entityManager.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
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
    public Customer findCustomerBy(String customerName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Customer customer = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            customer = (Customer) entityManager
                    .createNamedQuery(Customer.FIND_CUSTOMER_BY_NAME)
                    .setParameter(1, customerName)
                    .getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }

        if (customer == null) {
            log.info("Customer with name [{}] is not found", customerName);
        } else {
            log.info("Customer is: [{}]", customer);
        }

        return customer;
    }

    /**
     * Find supplier by their name
     *
     * @param companyName supplier's name
     * @return supplier instance
     */
    public Supplier findSupplierBy(String companyName) {
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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }

        if (supplier == null) {
            log.info("Customer with name [{}] is not found", companyName);
        } else {
            log.info("Customer is: [{}]", supplier);
        }

        return supplier;
    }

    /**
     * Find order by Id
     *
     * @param orderId order Id
     * @return order instance
     */
    public Order findOrderBy(int orderId) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Order order = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            order = (Order) entityManager
                    .createNamedQuery(Order.FIND_ORDER_BY_ID)
                    .setParameter(1, orderId)
                    .getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }

        if (order == null) {
            log.info("Customer with name [{}] is not found", orderId);
        } else {
            log.info("Customer is: [{}]", order);
        }

        return order;
    }

    /**
     * Find product by name
     *
     * @param productName product name
     * @return product instance
     */
    public Product findProductBy(String productName) {
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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Entity is not found");
        } finally {
            entityManager.close();
        }

        if (product == null) {
            log.info("Product [{}] is not found", productName);
        } else {
            log.info("Product is: [{}]", product);
        }

        return product;
    }

    /**
     * Update customer's phone number
     *
     * @param customerName customer's name
     * @param phone        phone number
     */
    public void updateCustomer(String customerName, String phone) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Customer customer = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            customer = findCustomerBy(customerName);
            customer.setPhone(phone);
            entityManager.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }

        if (customer != null) {
            log.info("Updated customer: [{}]", customer);
        }
    }

    /**
     * Update supplier's phone number
     *
     * @param companyName company name
     * @param phone       phone number
     */
    public void updateSupplier(String companyName, String phone) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Supplier supplier = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            supplier = findSupplierBy(companyName);
            supplier.setPhone(phone);
            entityManager.merge(supplier);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }

        if (supplier != null) {
            log.info("Updated supplier: [{}]", supplier);
        }
    }

    /**
     * Update product name and discontinuance
     *
     * @param productName    product name
     * @param isDiscontinued product is discontinued or not
     */
    public void updateProduct(String productName, boolean isDiscontinued) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Product product = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            product = findProductBy(productName);
            product.setProductName(productName);
            product.setDiscontinued(isDiscontinued);
            entityManager.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }

        if (product != null) {
            log.info("Updated product: [{}]", product);
        }
    }

    /**
     * Update order number, date, total amount
     *
     * @param orderId     order Id
     * @param orderNumber order No
     * @param totalAmount total amount for the order
     */
    public void updateOrder(int orderId, String orderNumber, double totalAmount) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Order order = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            order = findOrderBy(orderId);
            order.setOrderNumber(orderNumber);
            order.setTotalAmount(BigDecimal.valueOf(totalAmount));
            entityManager.merge(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }

        if (order != null) {
            log.info("Updated order: [{}]", order);
        }
    }

    /**
     * Merge product with order
     *
     * @param orderId     order Id
     * @param productName product name
     */
    public void mergeProductToOrder(int orderId, String productName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = findOrderBy(orderId);

            if (order == null) {
                log.error("Order is not available");
                entityManager.close();
                return;
            }
            var product = findProductBy(productName);

            if (product == null) {
                log.error("Product is not available");
                entityManager.close();
                return;
            }
            product.getOrderSet().add(order);
            order.getProductSet().add(product);
            entityManager.persist(order);
            entityManager.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete customer from database
     *
     * @param customerName customer's name
     */
    public void deleteCustomer(String customerName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var customer = findCustomerBy(customerName);

            if (customer == null) {
                log.error("Customer is not available");
                entityManager.close();
            } else {
                entityManager.remove(customer);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete supplier from database
     *
     * @param companyName company name
     */
    public void deleteSupplier(String companyName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var supplier = findSupplierBy(companyName);

            if (supplier == null) {
                log.error("Supplier is not available");
                entityManager.close();
            } else {
                entityManager.remove(supplier);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete order from database
     *
     * @param orderId order Id
     */
    public void deleteOrder(int orderId) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = findOrderBy(orderId);

            if (order == null) {
                log.error("Order is not available");
                entityManager.close();
            } else {
                entityManager.remove(order);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete product from database
     *
     * @param productName product name
     */
    public void deleteProduct(String productName) {
        var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var product = findProductBy(productName);

            if (product == null) {
                log.error("Product is not available");
                entityManager.close();
            } else {
                entityManager.remove(product);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception is", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Close EntityManagerFactory
     */
    public void closeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
