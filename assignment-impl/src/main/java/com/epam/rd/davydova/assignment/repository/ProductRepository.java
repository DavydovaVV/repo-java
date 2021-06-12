package com.epam.rd.davydova.assignment.repository;

import com.epam.rd.davydova.assignment.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is an interface for operations with database
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find product in database by name
     *
     * @param productName product name
     * @return Optional of product
     */
    Optional<Product> findByProductName(@Param("name") String productName);
}
