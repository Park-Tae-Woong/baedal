package com.sparta.baedal.controller;

import com.sparta.baedal.dto.FoodRequestDto;
import com.sparta.baedal.dto.FoodResponseDto;
import com.sparta.baedal.model.Food;
import com.sparta.baedal.model.Restaurant;
import com.sparta.baedal.repository.FoodRepository;
import com.sparta.baedal.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createFood(@RequestBody List<FoodRequestDto> foodRequestDto, @PathVariable Long restaurantId){
        foodService.createFood(foodRequestDto, restaurantId);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> getFood(@PathVariable Long restaurantId){
        return foodService.getFood(restaurantId);
    }

}
