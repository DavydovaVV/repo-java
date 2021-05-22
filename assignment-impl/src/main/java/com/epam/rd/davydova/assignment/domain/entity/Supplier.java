package com.epam.rd.davydova.assignment.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * This is a class that defines Supplier
 */
@Data
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private Set<Product> productSet;

    @Column(name = "company_name", unique = true, nullable = false, columnDefinition = "varchar(40)")
    private String companyName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;

    /**
     * Create supplier by company name
     *
     * @param companyName company name
     */
    public Supplier(String companyName) {
        this.companyName = companyName;
    }
}
