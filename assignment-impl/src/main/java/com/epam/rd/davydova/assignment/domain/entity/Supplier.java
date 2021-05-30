package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that defines Supplier
 */
@Data
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = Supplier.FIND_SUPPLIER_BY_NAME, query = "SELECT s FROM Supplier s WHERE s.companyName = ?1"),
        @NamedQuery(name = Supplier.FIND_ALL_SUPPLIERS, query = "SELECT s FROM Supplier s")})
public class Supplier {
    public static final String FIND_SUPPLIER_BY_NAME = "findSupplierByName";
    public static final String FIND_ALL_SUPPLIERS = "findAllSuppliers";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE)
    private List<Product> productList = new ArrayList<>();

    @Column(unique = true, nullable = false, columnDefinition = "varchar(40)")
    private String companyName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;
}