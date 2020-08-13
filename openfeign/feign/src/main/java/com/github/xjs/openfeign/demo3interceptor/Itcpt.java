package com.github.xjs.openfeign.demo3interceptor;

import feign.Param;
import feign.RequestLine;

public interface Itcpt {

    @RequestLine("GET /itcpt/getByUsername/{username}")
    public String getByUsername(@Param("username") String username);

}
