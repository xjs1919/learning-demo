package com.github.xjs.service.impl;

import com.github.xjs.domain.Order;
import com.github.xjs.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private List<Order> orderList;

    @Override
    public void create(Order order) {
        orderList.add(order);
    }

    @Override
    public Order getOrder(Long id) {
        List<Order> findUserList = orderList.stream().filter(orderItem -> orderItem.getId().equals(id)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        }
        return null;
    }

    @Override
    public void update(Order order) {
        orderList.stream().filter(orderItem -> orderItem.getId().equals(order.getId())).forEach(orderItem -> {
            BeanUtils.copyProperties(order,orderItem);
        });
    }

    @Override
    public void delete(Long id) {
        Order order = getOrder(id);
        if (order != null) {
            orderList.remove(order);
        }
    }

    @PostConstruct
    public void initData() {
        orderList = new ArrayList<>();
        orderList.add(Order.builder()
                .id(1L)
                .userId(1L)
                .productId(1L)
                .money(new BigDecimal(100))
                .count(1)
                .status(0)
                .build());
        orderList.add(Order.builder()
                .id(2L)
                .userId(1L)
                .productId(2L)
                .money(new BigDecimal(200))
                .count(2)
                .status(0)
                .build());
    }
}
