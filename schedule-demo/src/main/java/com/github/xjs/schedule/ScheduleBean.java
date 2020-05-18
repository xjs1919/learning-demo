package com.github.xjs.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.*;

@Slf4j
public class ScheduleBean {

    @Scheduled(fixedRate = 1000 * 3)
    public void fixedRate(){
        log.info(Thread.currentThread().getName()+",fixedRate");
    }

    //minute, hour, day of month, month and day of week
    //每一分钟执行一次
    @Scheduled(cron = "0 * *  * * * ")
    public void cron(){
        log.info(Thread.currentThread().getName()+",cron");
    }
}
