package com.github.xjs.feign;

public class UserContext {

    private static final ThreadLocal<Long> TL = new ThreadLocal<>();

    public static void setUserId(Long userId){
        TL.set(userId);
    }

    public static Long getUserId(){
        return TL.get();
    }

    public static void removeUserId(){
        TL.remove();
    }
}
