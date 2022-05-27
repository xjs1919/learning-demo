package com.example.demo.user;

/**
 * @author xujiashuai
 * @date 2022/5/27 14:53
 **/
public class UserHolder {

    private static ThreadLocal<String> username = new ThreadLocal<>();

    public static String get(){
        return username.get();
    }

    public static void set(String user){
        username.set(user);
    }

    public static void remove(){
        username.remove();
    }

}
