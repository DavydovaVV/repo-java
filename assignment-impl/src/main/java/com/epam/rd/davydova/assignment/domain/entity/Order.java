package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * This is a class that defines Order
 */
@Data
@Entity
@Table(name = "Order")
@Access(AccessType.FIELD)
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "orderSet")
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSet;

    @Column(unique = true, columnDefinition = "varchar(10)")
    private String orderNumber;

    @ManyToOne
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;
}
