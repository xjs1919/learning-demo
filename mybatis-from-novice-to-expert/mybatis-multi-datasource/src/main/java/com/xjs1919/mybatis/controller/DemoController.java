package com.xjs1919.mybatis.controller;

import com.xjs1919.mybatis.dao.ds1.UserMapper;
import com.xjs1919.mybatis.domain.Goods;
import com.xjs1919.mybatis.domain.User;
import com.xjs1919.mybatis.service.GoodsService;
import com.xjs1919.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:15
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @GetMapping("user/selectById")
    public User userSelectById(@RequestParam("id")Integer id){
        return userService.selectById(id);
    }

    @GetMapping("goods/selectById")
    public Goods goodsSelectById(@RequestParam("id")Integer id){
        return goodsService.selectById(id);
    }

    @GetMapping("transaction")
    public int transaction(){
        return goodsService.transaction();
    }

//    @GetMapping("transaction2")
//    public int transaction2(){
//        return goodsService.transaction2();
//    }

}
