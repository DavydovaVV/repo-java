package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
    private Set<Product> productSet;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(40)")
    private String companyName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;
}
