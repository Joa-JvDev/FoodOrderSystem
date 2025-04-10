package jva.dev.foodordersystem.controller;

import jva.dev.foodordersystem.domain.entity.Items;
import jva.dev.foodordersystem.domain.entity.OrderDetails;
import jva.dev.foodordersystem.domain.entity.Orders;
import jva.dev.foodordersystem.domain.entity.UserEntity;
import jva.dev.foodordersystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public Orders create() {
        return orderService.create();
    }

}
