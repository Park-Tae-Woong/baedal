package com.sparta.baedal.repository;

import com.sparta.baedal.model.Food;
import com.sparta.baedal.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurant(Restaurant restaurant);

    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String name);
}
