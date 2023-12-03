package com.xjs1919.dao;

import com.xjs1919.domain.ShoppingCart;
import java.util.List;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCart row);

    ShoppingCart selectByPrimaryKey(Long id);

    List<ShoppingCart> selectAll();

    int updateByPrimaryKey(ShoppingCart row);
}