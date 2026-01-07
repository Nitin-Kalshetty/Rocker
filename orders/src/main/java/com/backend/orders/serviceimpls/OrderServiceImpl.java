package com.backend.orders.serviceimpls;

import com.backend.orders.dtos.OrderRequestDTO;
import com.backend.orders.dtos.OrderResponseDTO;
import com.backend.orders.repositories.OrderRepository;
import com.backend.orders.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        return null;
    }
}
