package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * This is a class that defines Order
 */
@Data
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSet;

    @Column(name = "order_number", unique = true, columnDefinition = "varchar(10)")
    private String orderNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    /**
     * Create order
     *
     * @param orderNumber internal order number
     * @param customer    customer
     * @param orderDate   order date
     * @param totalAmount total amount
     */
    public Order(String orderNumber, Customer customer, Date orderDate, BigDecimal totalAmount) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
}
