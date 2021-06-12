package com.epam.rd.davydova.assignment.repository;

import com.epam.rd.davydova.assignment.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is an interface for operations with database
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Find supplier in database by name
     *
     * @param companyName company name
     * @return Optional of supplier
     */
    Optional<Supplier> findByCompanyName(String companyName);
}
