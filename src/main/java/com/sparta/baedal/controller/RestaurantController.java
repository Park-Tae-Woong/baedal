package com.sparta.baedal.controller;

import com.sparta.baedal.dto.RestaurantRequestDto;
import com.sparta.baedal.model.Restaurant;
import com.sparta.baedal.repository.RestaurantRepository;
import com.sparta.baedal.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/api/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurants = restaurantService.createRestaurant(restaurantRequestDto);
        return restaurants;
    }

    @GetMapping("/api/restaurants")
    public List<Restaurant> getRestaurant(){
        return restaurantService.getRestaurant();
    }




}
