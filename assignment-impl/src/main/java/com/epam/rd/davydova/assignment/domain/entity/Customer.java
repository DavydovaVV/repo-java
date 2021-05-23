package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a class that defines Customer
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = Customer.FIND_CUSTOMER_BY_NAME, query = "SELECT c FROM Customer c WHERE c.customerName = ?1")
public class Customer {
    public static final String FIND_CUSTOMER_BY_NAME = "findCustomerByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orderSet = new HashSet<>();

    @NonNull
    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String customerName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
