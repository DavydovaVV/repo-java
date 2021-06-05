package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that defines Customer
 */
@Data
@Entity
@RequiredArgsConstructor
@Accessors(chain = true)
public class Customer {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE)
    private List<Order> orderList = new ArrayList<>();

    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String customerName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;
}