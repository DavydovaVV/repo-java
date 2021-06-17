package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is a class that defines Order
 */
@Data
@Entity
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "[order]")
public class Order {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList = new ArrayList<>();

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