package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * This is a class that defines OrderProduct
 */
@Data
@Entity
@Table(name = "OrderProduct")
@Access(AccessType.FIELD)
public class OrderProduct {
    @Id
    private int orderId;

    @Id
    private int productId;

    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id")
    @MapsId
    private Product product;
}
