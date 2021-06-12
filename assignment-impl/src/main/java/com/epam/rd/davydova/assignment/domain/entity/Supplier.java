package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that defines Supplier
 */
@Data
@Entity
@RequiredArgsConstructor
@Accessors(chain = true)
public class Supplier {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long supplierId;

    @ToString.Exclude
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.MERGE)
    private List<Product> productList = new ArrayList<>();

    @Column(unique = true, nullable = false, columnDefinition = "varchar(40)")
    private String companyName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;
}