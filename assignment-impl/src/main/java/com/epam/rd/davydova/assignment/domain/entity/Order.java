package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

    @OneToOne(mappedBy = "order")
    private OrderProduct orderProduct;

    @Column(unique = true)
    private String orderNumber;

    @OneToOne
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private BigDecimal totalAmount;
}
