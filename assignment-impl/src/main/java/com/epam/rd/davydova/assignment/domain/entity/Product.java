package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * This is a class that defines Product
 */
@Data
@Entity
@Table(name = "Product")
@Access(AccessType.FIELD)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "productSet")
    private Set<Order> orderSet;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String productName;

    @ManyToOne
    @JoinColumn(unique = true, nullable = false, name = "supplier_id")
    private Supplier supplier;

    @Column(precision = 12, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private boolean isDisconnected;
}
