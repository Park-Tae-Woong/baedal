package com.sparta.baedal.service;

import com.sparta.baedal.dto.FoodRequestDto;
import com.sparta.baedal.dto.RestaurantRequestDto;
import com.sparta.baedal.model.Food;
import com.sparta.baedal.model.Restaurant;
import com.sparta.baedal.repository.FoodRepository;
import com.sparta.baedal.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public void createFood(List<FoodRequestDto> foodRequestDto, Long retaurantId) {
        List<Food> foods = new ArrayList<>();
        Restaurant restaurant = restaurantRepository.findById(retaurantId).get();
        for (int i = 0; i < foodRequestDto.size(); i++) {
            int price = foodRequestDto.get(i).getPrice();
            String name = foodRequestDto.get(i).getName();
            if (price >= 100 || price <= 1000000) {
                System.out.println("price : " + price + " name : " + name);
                if (price % 100 == 0) {
                    System.out.println("price : " + price + " name : " + name);
                } else {
                    throw new IllegalArgumentException("100원 단위 적어주삼");
                }
            } else {
                throw new IllegalArgumentException("100원 ~ 1000000원 까지만 적어주삼");
            }

            Optional<Food> foodsInRestaurant = foodRepository.findFoodByRestaurantAndName(restaurant, name);
            if (foodsInRestaurant.isPresent()) {
                throw new IllegalArgumentException("중복된 메뉴입니다.");
            }
            Food food = new Food(restaurant, name, price);
            foods.add(food);
        }
        foodRepository.saveAll(foods);
    }

}
