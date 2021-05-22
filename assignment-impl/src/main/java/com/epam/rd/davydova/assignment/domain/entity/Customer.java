package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * This is a class that defines Customer
 */
@Data
@Entity
@Table(name = "Customer")
@Access(AccessType.FIELD)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Order> orderSet;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String customerName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;
}
