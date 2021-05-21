package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(unique = true, nullable = false)
    private String customerName;

    @Column
    private String phone;
}
