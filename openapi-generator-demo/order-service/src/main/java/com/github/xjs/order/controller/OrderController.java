package com.github.xjs.order.controller;

import com.github.xjs.order.dto.OrderDto;
import com.github.xjs.user.UserControllerApi;
import com.github.xjs.user.invoker.ApiClient;
import com.github.xjs.user.model.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    Map<Integer, OrderDto> db = List.of(new OrderDto(1, "Apple", 10), new OrderDto(2, "XiaoMi", 20))
                                    .stream()
                                    .collect(Collectors.toMap(OrderDto::getId, Function.identity()));

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable int id){
        OrderDto orderDto = db.get(id);
        int userId = orderDto.getUserId();
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://localhost:8081");
        UserControllerApi userControllerApi = new UserControllerApi(apiClient);
        UserDto user = userControllerApi.getById(userId);
        orderDto.setUser(new com.github.xjs.order.dto.UserDto(user.getId(), user.getName()));
        return orderDto;
    }

}

