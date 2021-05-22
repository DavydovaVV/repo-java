package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * This is a class that defines Product
 */
@Data
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "productSet")
    private Set<Order> orderSet;

    @Column(name = "product_name", unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String productName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true, nullable = false, name = "supplier_id")
    private Supplier supplier;

    @Column(name = "unit_price", precision = 12, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "is_disconnected", nullable = false, columnDefinition = "default 'false'")
    private boolean isDisconnected;
}
