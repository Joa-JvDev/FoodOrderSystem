package jva.dev.foodordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import jva.dev.foodordersystem.domain.entity.*;
import jva.dev.foodordersystem.domain.enums.StatusOrder;
import jva.dev.foodordersystem.dto.response.OrderResponseDTO;
import jva.dev.foodordersystem.exception.APIError;
import jva.dev.foodordersystem.exception.ApplicationExeption;
import jva.dev.foodordersystem.mapper.OrderMapper;
import jva.dev.foodordersystem.service.repository.OrderDetailsRepository;
import jva.dev.foodordersystem.service.repository.OrderRepository;
import jva.dev.foodordersystem.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderMapper orderMapper;

    public OrderResponseDTO create() {
        UserEntity user = findAutenticatedUser();
        Orders orders = new Orders();
        List<OrderDetails> detailsList = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        if (user.getCart().getItems().isEmpty()) {
            throw new ApplicationExeption(APIError.CART_IS_EMPTY);
        }

        for (Items items : user.getCart().getItems()) {
            OrderDetails detail = new OrderDetails();
            detail.setQuantity(items.getQuantity());

            BigDecimal itemTotalPrice = items.getUnitPrice().multiply(new BigDecimal(items.getQuantity()));
            detail.setUnitPrice(itemTotalPrice);
            detail.setItems(items);

            orderDetailsRepository.save(detail);
            detailsList.add(detail);
            totalPrice = totalPrice.add(itemTotalPrice);
        }

        orders.setDetails(detailsList);
        orders.setTotalPrice(totalPrice);
        orders.setOrderDate(LocalDate.now());
        orders.setStatus(StatusOrder.PENDING);
        user.getCart().getItems().clear();
        user.getOrders().add(orders);
        orderRepository.save(orders);
        userRepository.save(user);
        return orderMapper.toResponseDTO(orders);
    }

    public List<Orders> getOrdersByUserId() {
        UserEntity user = findAutenticatedUser();
        return user.getOrders();
    }

    private UserEntity findAutenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUserEntityByEmail(auth.getName()).orElseThrow(EntityNotFoundException::new);
    }


}
