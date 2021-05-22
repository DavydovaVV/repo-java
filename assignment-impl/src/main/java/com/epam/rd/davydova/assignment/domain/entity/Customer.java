package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * This is a class that defines Customer
 */
@Data
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orderSet;

    @Column(name = "customer_name", unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String customerName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;

    /**
     * Create customer by customer name
     *
     * @param customerName customer name
     */
    public Customer(String customerName) {
        this.customerName = customerName;
    }
}
