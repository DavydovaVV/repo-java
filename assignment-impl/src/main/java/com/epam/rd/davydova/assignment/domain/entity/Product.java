package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that defines Product
 */
@Data
@Entity
@NamedQuery(name = Product.FIND_PRODUCT_BY_NAME, query = "SELECT p FROM Product p WHERE p.productName = ?1")
public class Product {
    public static final String FIND_PRODUCT_BY_NAME = "findProductByName";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @ManyToMany(mappedBy = "productList", cascade = CascadeType.REMOVE)
    private List<Order> orderList = new ArrayList<>();

    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String productName;

    @ManyToOne
    @JoinColumn(unique = true, nullable = false, name = "supplier_id")
    private Supplier supplier;

    @Column(precision = 12, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private boolean isDiscontinued;

    /**
     * Add order to list
     *
     * @param order order
     */
    public void addToList(Order order) {
        orderList.add(order);
    }
}