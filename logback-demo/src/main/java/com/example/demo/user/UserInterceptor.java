package com.example.demo.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xujiashuai
 * @date 2022/5/27 15:06
 **/
@Configuration
public class UserInterceptor implements HandlerInterceptor {

    private static Map<String, String> usersDB = new HashMap(){
        {
            put("1111", "xjs1919");
        }
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie useridCookie = WebUtils.getCookie(request, "userid");
        if(useridCookie != null){
            String userid = useridCookie.getValue();
            String username = usersDB.get(userid);
            UserHolder.set(username);
        }
        return true;
    }

}
