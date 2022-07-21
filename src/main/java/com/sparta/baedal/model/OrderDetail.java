package com.sparta.baedal.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderid", nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "foodid", nullable = false)
    private Food food;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;
}
