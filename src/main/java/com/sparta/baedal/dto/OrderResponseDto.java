package com.sparta.baedal.dto;

import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {
    private String restaurantName;

    private List<OrderDetailResponseDto> foods;

    private int deliveryFee;

    private int totalPrice;


}
