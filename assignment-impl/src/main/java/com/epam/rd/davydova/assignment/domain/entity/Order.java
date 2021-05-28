package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

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
@NamedQueries({
        @NamedQuery(name = Order.FIND_ORDER_BY_NUMBER,
                query = "SELECT o FROM Order o WHERE o.orderNumber = ?1"),
        @NamedQuery(name = Order.FIND_TOTAL_AMOUNT_PER_ORDER,
                query = "SELECT SUM(totalAmount) FROM Order o WHERE o.orderId = ?1")})
@Table(name = "[order]")
public class Order {
    public static final String FIND_ORDER_BY_NUMBER = "findOrderById";
    public static final String FIND_TOTAL_AMOUNT_PER_ORDER = "findTotalSumPerOrder";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList = new ArrayList<>();

    @Column(unique = true, columnDefinition = "varchar(10), default 'n/a'")
    private String orderNumber;

    @ManyToOne
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    /**
     * Add product to list
     *
     * @param product product
     */
    public void addToList(Product product) {
        productList.add(product);
    }
}