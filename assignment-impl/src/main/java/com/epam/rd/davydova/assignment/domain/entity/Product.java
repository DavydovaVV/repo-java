package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a class that defines Product
 */
@Data
@Entity
@NamedQuery(name = Product.FIND_PRODUCT_BY_NAME, query = "SELECT p FROM Product p WHERE p.productName = ?1")
public class Product {
    public static final String FIND_PRODUCT_BY_NAME = "findProductByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @ManyToMany(mappedBy = "productSet")
    private Set<Order> orderSet = new HashSet<>();

    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String productName;

    @ManyToOne
    @JoinColumn(unique = true, nullable = false, name = "supplier_id")
    private Supplier supplier;

    @Column(precision = 12, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private boolean isDiscontinued;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", supplier=" + supplier +
                ", unitPrice=" + unitPrice +
                ", isDiscontinued=" + isDiscontinued +
                '}';
    }
}
