package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is a class that defines Supplier
 */
@Data
@Entity
@Table(name = "Supplier")
@Access(AccessType.FIELD)
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    @Column(unique = true, nullable = false)
    private String companyName;

    @Column
    private String phone;
}
