package com.sparta.baedal.dto;

import com.sparta.baedal.model.Restaurant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequestDto {
    private String name;
    private int price;
}
