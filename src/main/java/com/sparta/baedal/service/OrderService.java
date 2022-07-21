package com.sparta.baedal.service;


import com.sparta.baedal.dto.OrderDetailRequestDto;
import com.sparta.baedal.dto.OrderDetailResponseDto;
import com.sparta.baedal.dto.OrderRequestDto;
import com.sparta.baedal.dto.OrderResponseDto;
import com.sparta.baedal.model.Food;
import com.sparta.baedal.model.Orders;
import com.sparta.baedal.model.OrderDetail;
import com.sparta.baedal.model.Restaurant;
import com.sparta.baedal.repository.FoodRepository;
import com.sparta.baedal.repository.OrderDetailRepository;
import com.sparta.baedal.repository.OrderRepository;
import com.sparta.baedal.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto){
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException("즐")
        );
        int totalPrice = restaurant.getDeliveryFee();
        Orders orders = Orders.builder()
                .restaurant(restaurant)
                .totalPrice(totalPrice)
                .build();


        List<OrderDetailResponseDto> orderDetailResponseDtos = new ArrayList<>();

        for(OrderDetailRequestDto foods: orderRequestDto.getFoods()){
            Food food = foodRepository.findById(foods.getFoodId()).orElseThrow(
                    () -> new IllegalArgumentException("똥")
            );
            int price = foods.getQuantity() * food.getPrice();
            totalPrice += price;

            OrderDetail orderDetail = OrderDetail.builder()
                    .food(food)
                    .quantity(foods.getQuantity())
                    .orders(orders)
                    .price(price)
                    .build();
            orderDetailRepository.save(orderDetail);

            OrderDetailResponseDto orderDetailResponseDto = OrderDetailResponseDto.builder()
                    .foodName(food.getName())
                    .quantity(foods.getQuantity())
                    .price(price)
                    .build();

            orderDetailResponseDtos.add(orderDetailResponseDto);

        }
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .restaurantName(restaurant.getName())
                .foods(orderDetailResponseDtos)
                .deliveryFee(restaurant.getDeliveryFee())
                .totalPrice(totalPrice)
                .build();

        return orderResponseDto;
    }
    public List<OrderResponseDto> getOrders() {
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        List<Orders> orders = orderRepository.findAll();

        for(int i=0; i < orders.size(); i++ ) {
            List<OrderDetailResponseDto> orderDetailResponseDtos = new ArrayList<>();
            List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrders(orders.get(i));

            for(int j=0; j < orderDetails.size(); j++) {
                orderDetailResponseDtos.add(new OrderDetailResponseDto(orderDetails.get(j).getFood().getName(),orderDetails.get(j).getQuantity(),orderDetails.get(j).getPrice()));

            }
            orderResponseDtos.add(new OrderResponseDto(orders.get(i).getRestaurant().getName(),orderDetailResponseDtos,orders.get(i).getRestaurant().getDeliveryFee(),orders.get(i).getTotalPrice()));
        }

        return orderResponseDtos;
    }


}
