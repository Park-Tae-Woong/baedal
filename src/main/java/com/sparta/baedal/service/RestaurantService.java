package com.sparta.baedal.service;

import com.sparta.baedal.dto.RestaurantRequestDto;
import com.sparta.baedal.model.Restaurant;
import com.sparta.baedal.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    //post
    @Transactional
    public Restaurant createRestaurant(RestaurantRequestDto restaurantRequestDto){

        Restaurant restaurant = new Restaurant(restaurantRequestDto);
        if (restaurant.getMinOrderPrice() >= 1000 && restaurant.getMinOrderPrice() <= 100000) {
            if (restaurant.getMinOrderPrice() % 100 == 0){
                if (restaurant.getDeliveryFee() >= 0 && restaurant.getDeliveryFee() <=10000) {
                    if (restaurant.getDeliveryFee() % 500 == 0){
                        restaurantRepository.save(restaurant);
                        return restaurant;
                    } else {
                        throw new IllegalArgumentException("500원 단위 적어주삼");
                    }
                } else {
                    throw new IllegalArgumentException("10000원 이하 적어주삼");
                }
            } else {
                throw new IllegalArgumentException("100원 단위 적어주삼");
            }
        } else {
            throw new IllegalArgumentException("1000원 이상, 100000원 이하 적어주삼");
        }
    }

    //get
    public List<Restaurant> getRestaurant(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }
}
