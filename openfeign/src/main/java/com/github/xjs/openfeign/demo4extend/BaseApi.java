package com.github.xjs.openfeign.demo4extend;

import feign.Param;
import feign.RequestLine;

public interface BaseApi<T> {
    @RequestLine("GET /extend/getById/{id}")
    T getById(@Param("id")String id);
}
