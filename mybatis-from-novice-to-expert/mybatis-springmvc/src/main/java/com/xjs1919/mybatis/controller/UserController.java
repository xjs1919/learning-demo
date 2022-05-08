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
    @GetMapping("/selectByIdUseTemplate")
    public User selectByIdUseTemplate(@RequestParam("id")Integer id){
        return userService.selectByIdUseTemplate(id);
    }

    @GetMapping("/selectByIdUseDaoSupport")
    public User selectByIdUseDaoSupport(@RequestParam("id")Integer id){
        return userService.selectByIdUseDaoSupport(id);
    }

    @GetMapping("/selectByIdUseSingleMapper")
    public User selectByIdUseSingleMapper(@RequestParam("id")Integer id){
        return userService.selectByIdUseSingleMapper(id);
    }

    @GetMapping("/selectByIdMapperScan")
    public User selectByIdMapperFactoryBean(@RequestParam("id")Integer id){
        return userService.selectByIdUseMapperScan(id);
    }

    @GetMapping("/selectByIdUseTransaction")
    public User selectByIdUseTransaction(@RequestParam("id")Integer id){
        return userService.selectByIdUseTransaction(id);
    }

    @GetMapping("/saveUseTransactional")
    public int save(){
        return userService.saveUseTransactional();
    }

    @GetMapping("/saveManualUseTransactionManager")
    public int saveManual(){
        return userService.saveManualUseTransactionManager();
    }

    @GetMapping("/saveManualUseTransactionTemplate")
    public int saveManualUseTransactionTemplate(){
        return userService.saveManualUseTransactionTemplate();
    }

    @GetMapping("/batchUpdate")
    public boolean batchUpdate(){
        return userService.batchUpdate();
    }



}
