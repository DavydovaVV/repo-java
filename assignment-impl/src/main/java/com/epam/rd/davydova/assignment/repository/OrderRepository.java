package com.epam.rd.davydova.assignment.repository;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is an interface for operations with database
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find order in database by number
     *
     * @param orderNumber order number
     * @return Optional of order
     */
    Optional<Order> findByOrderNumber(String orderNumber);
}
