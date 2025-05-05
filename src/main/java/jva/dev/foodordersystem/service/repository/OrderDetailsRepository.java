package jva.dev.foodordersystem.service.repository;

import jva.dev.foodordersystem.domain.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
