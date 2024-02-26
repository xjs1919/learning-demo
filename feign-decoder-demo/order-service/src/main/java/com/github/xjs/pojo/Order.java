package com.github.xjs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    private Long id;
    private String goodsName;
    private String price;
    private Long userId;
    private User user;

}
