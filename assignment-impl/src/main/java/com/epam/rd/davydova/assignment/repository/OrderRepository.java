package com.epam.rd.davydova.assignment.repository;

import com.epam.rd.davydova.assignment.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * This is an interface for operations with database
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.number = :number")
    Optional<Order> findByNumber(@Param("number") String orderNumber);
}
