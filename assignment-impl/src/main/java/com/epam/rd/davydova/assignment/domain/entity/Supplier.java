package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a class that defines Supplier
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = Supplier.FIND_SUPPLIER_BY_NAME, query = "SELECT s FROM Supplier s WHERE s.companyName = ?1")
public class Supplier {
    public static final String FIND_SUPPLIER_BY_NAME = "findSupplierByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private Set<Product> productSet = new HashSet<>();

    @NonNull
    @Column(unique = true, nullable = false, columnDefinition = "varchar(40)")
    private String companyName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", companyName='" + companyName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
