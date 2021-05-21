package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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

    @OneToOne(mappedBy = "order")
    private OrderProduct orderProduct;

    @Column(unique = true, nullable = false)
    private String productName;

    @OneToOne
    @JoinColumn(unique = true, nullable = false, name = "supplier_id")
    private Supplier supplier;

    @Column
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private boolean isDisconnected;
}
