package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a class that defines Order
 */
@Data
@Entity
@NamedQuery(name = Order.FIND_ORDER_BY_ID, query = "SELECT o FROM Order o WHERE o.orderId = ?1")
@Table(name = "[order]")
public class Order {
    public static final String FIND_ORDER_BY_ID = "findOrderById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSet = new HashSet<>();

    @Column(unique = true, columnDefinition = "varchar(10), default 'n/a'")
    private String orderNumber;

    @ManyToOne
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
