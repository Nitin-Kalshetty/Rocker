package com.backend.orders.controllers;

import com.backend.orders.dtos.OrderRequestDTO;
import com.backend.orders.dtos.OrderResponseDTO;
import com.backend.orders.models.Order;
import com.backend.orders.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody Order order){
        OrderResponseDTO orderResponse = orderService.createOrder(new OrderRequestDTO());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}
