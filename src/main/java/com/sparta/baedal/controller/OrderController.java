package com.sparta.baedal.controller;

import com.sparta.baedal.dto.OrderRequestDto;
import com.sparta.baedal.dto.OrderResponseDto;
import com.sparta.baedal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }

}
