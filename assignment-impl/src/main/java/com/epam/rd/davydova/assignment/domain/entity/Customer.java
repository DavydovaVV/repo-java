package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that defines Customer
 */
@Data
@Entity
@NamedQuery(name = Customer.FIND_CUSTOMER_BY_NAME, query = "SELECT c FROM Customer c WHERE c.customerName = ?1")
public class Customer {
    public static final String FIND_CUSTOMER_BY_NAME = "findCustomerByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Order> orderList = new ArrayList<>();

    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String customerName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;

    /**
     * Add order to list
     *
     * @param order order
     */
    public void addToList(Order order) {
        orderList.add(order);
    }
}
