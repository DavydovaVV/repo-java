package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that defines Product
 */
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Entity
public class Product {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @ToString.Exclude
    @ManyToMany(mappedBy = "productList", cascade = CascadeType.MERGE)
    private List<Order> orderList = new ArrayList<>();

    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String productName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(unique = true, nullable = false, name = "supplier_id")
    private Supplier supplier;

    @Column(precision = 12, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private boolean isDiscontinued;
}