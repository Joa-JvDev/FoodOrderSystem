package jva.dev.foodordersystem.service.repository;

import jva.dev.foodordersystem.domain.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
