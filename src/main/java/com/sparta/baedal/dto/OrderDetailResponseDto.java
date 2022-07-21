package com.sparta.baedal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderDetailResponseDto {
    private String foodName;
    private int quantity;
    private int price;
}
