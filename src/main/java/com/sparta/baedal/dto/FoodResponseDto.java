package com.sparta.baedal.dto;

import com.sparta.baedal.model.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FoodResponseDto {
    private Long id;
    private String name;
    private int price;

    public FoodResponseDto(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.price = food.getPrice();
    }
}
