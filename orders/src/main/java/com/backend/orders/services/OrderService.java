package com.backend.orders.services;

import com.backend.orders.dtos.OrderRequestDTO;
import com.backend.orders.dtos.OrderResponseDTO;

public interface OrderService {

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
}
