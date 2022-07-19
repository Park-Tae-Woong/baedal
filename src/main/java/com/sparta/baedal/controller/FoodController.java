package com.sparta.baedal.controller;

import com.sparta.baedal.dto.FoodRequestDto;
import com.sparta.baedal.model.Food;
import com.sparta.baedal.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createFood(@RequestBody List<FoodRequestDto> foodRequestDto, @PathVariable Long restaurantId){
        foodService.createFood(foodRequestDto, restaurantId);

    }

}
