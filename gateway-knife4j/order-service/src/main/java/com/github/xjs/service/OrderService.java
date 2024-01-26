package com.github.xjs.service;

import com.github.xjs.domain.Order;

public interface OrderService {
    void create(Order order);

    Order getOrder(Long id);

    void update(Order order);

    void delete(Long id);
}
