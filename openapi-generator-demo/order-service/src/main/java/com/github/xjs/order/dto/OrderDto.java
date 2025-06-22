package com.github.xjs.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private String goodsName;
    private int userId;
    private UserDto user;

    public OrderDto(int id, String goodsName, int userId) {
        this.id = id;
        this.goodsName = goodsName;
        this.userId = userId;
    }
}
