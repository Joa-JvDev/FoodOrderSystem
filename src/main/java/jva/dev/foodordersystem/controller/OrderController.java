package jva.dev.foodordersystem.controller;

import jva.dev.foodordersystem.domain.entity.Orders;
import jva.dev.foodordersystem.domain.entity.UserEntity;
import jva.dev.foodordersystem.dto.response.OrderResponseDTO;
import jva.dev.foodordersystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> create() {
        return new ResponseEntity<>(orderService.create(), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getOrdersOfUser() {
        return new ResponseEntity<>(orderService.getOrdersByUserId(), HttpStatus.OK);
    }

}
