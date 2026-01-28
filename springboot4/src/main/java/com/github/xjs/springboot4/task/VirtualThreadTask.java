package com.github.xjs.springboot4.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class VirtualThreadTask {

    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.SECONDS)
    public void demoTask(){
        log.info("demo task start, is virtual:{}", Thread.currentThread().isVirtual());
    }
}
