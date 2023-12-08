package com.xjs1919.dao;

import com.xjs1919.domain.ShoppingCart;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCart row);

    int insertSelective(ShoppingCart row);

    ShoppingCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCart row);

    int updateByPrimaryKey(ShoppingCart row);
}