package com.sparta.baedal.repository;

import com.sparta.baedal.model.Orders;
import com.sparta.baedal.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrders(Orders orders);
}
