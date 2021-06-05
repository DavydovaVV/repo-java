package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that defines Customer
 */
@Data
@Entity
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = Customer.FIND_CUSTOMER_BY_NAME, query = "SELECT c FROM Customer c WHERE c.customerName = ?1"),
        @NamedQuery(name = Customer.FIND_ALL_CUSTOMERS, query = "SELECT c FROM Customer c")})
public class Customer {
    public static final String FIND_CUSTOMER_BY_NAME = "findCustomerByName";
    public static final String FIND_ALL_CUSTOMERS = "findAllCustomers";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Order> orderList = new ArrayList<>();

    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String customerName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;
}