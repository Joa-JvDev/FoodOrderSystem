package jva.dev.foodordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import jva.dev.foodordersystem.domain.entity.*;
import jva.dev.foodordersystem.domain.enums.StatusOrder;
import jva.dev.foodordersystem.repository.OrderDetailsRepository;
import jva.dev.foodordersystem.repository.OrderRepository;
import jva.dev.foodordersystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public Orders create() {
        UserEntity user = findAutenticatedUser();
        Orders orders = new Orders();
        List<OrderDetails> detailsList = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Items items : user.getCart().getItems()) {
            OrderDetails detail = new OrderDetails();
            detail.setQuantity(items.getQuantity());

            // Multiplicar el precio unitario por la cantidad
            BigDecimal itemTotalPrice = items.getUnitPrice().multiply(new BigDecimal(items.getQuantity()));

            detail.setUnitPrice(itemTotalPrice);

            orderDetailsRepository.save(detail);
            detailsList.add(detail);

            // Acumular el precio total de la orden
            totalPrice = totalPrice.add(itemTotalPrice);
        }

        orders.setDetails(detailsList);
        orders.setTotalPrice(totalPrice);
        orders.setOrderDate(LocalDate.now());
        orders.setStatus(StatusOrder.PENDING);
        user.getCart().getItems().clear();

        return orderRepository.save(orders);
    }

    public void cancel(Long id){

    }

    private UserEntity findAutenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findUserEntityByEmail(auth.getName()).orElseThrow(EntityNotFoundException::new);
        return user;
    }

}
