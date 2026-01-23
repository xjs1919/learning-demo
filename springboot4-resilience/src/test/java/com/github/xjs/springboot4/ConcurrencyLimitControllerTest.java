package com.github.xjs.springboot4;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class ConcurrencyLimitControllerTest {
    public static void main(String[] args) throws Exception{
        System.out.println("start at " + LocalDateTime.now());
        int threadCount = 5;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch stop = new CountDownLatch(threadCount);
        for(int i=0; i<threadCount; i++){
            new Thread(()->{
                try {
                    start.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String res = new RestTemplate().getForObject("http://localhost:8080/api/concurrency-limit/demo2", String.class);
                System.out.println(res);
                stop.countDown();
            }).start();
        }
        start.countDown();
        stop.await();
        System.out.println("end at " + LocalDateTime.now());
    }
}