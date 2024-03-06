package com.github.xjs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DemoController {
    /**
     * 首页
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request)
    {
        //将用户信息保存到Request对象中
        request.setAttribute("name","张三");
        //返回首页
        return "index";
    }
}
