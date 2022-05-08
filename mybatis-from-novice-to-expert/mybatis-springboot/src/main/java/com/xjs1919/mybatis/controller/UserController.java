package com.xjs1919.mybatis.controller;

import com.xjs1919.mybatis.domain.User;
import com.xjs1919.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiashuai.xujs
 * @date 2022/3/24 13:46
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/selectById")
    public User selectById(@RequestParam("id")Integer id){
        return userService.selectById(id);
    }

    @GetMapping("/selectByIdUseSqlSession")
    public User selectByIdUseSqlSession(@RequestParam("id")Integer id){
        return userService.selectByIdUseSqlSession(id);
    }

    @GetMapping("/saveUseTransactional")
    public int saveUseTransactional(){
        return userService.saveUseTransactional();
    }

    @GetMapping("/saveManualUseTransactionManager")
    public int saveManualUseTransactionManager(){
        return userService.saveManualUseTransactionManager();
    }

    @GetMapping("/batchUpdate")
    public boolean batchUpdate(){
        return userService.batchUpdate();
    }

}
