package com.github.xjs.springboot4.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VirtualThreadService {

    @Async
    public void async() {
        log.info("is virtual:{}", Thread.currentThread().isVirtual());
    }
}
