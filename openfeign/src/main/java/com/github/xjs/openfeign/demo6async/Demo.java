package com.github.xjs.openfeign.demo6async;

import feign.RequestLine;

import java.util.concurrent.CompletableFuture;

public interface Demo {
    @RequestLine("GET /async/hello")
    CompletableFuture<String> hello();
}
