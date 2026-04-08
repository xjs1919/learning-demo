package com.github.xjs.springboot4;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

public class ConcurrencyLimitControllerTest {
    public static void main(String[] args) throws Exception{
        Thread t[] = new Thread[10];
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch stop = new CountDownLatch(10);
        for(int i=0; i<10; i++){
            t[i] = new Thread(()->{
                try {
                    start.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String res = new RestTemplate().getForObject("http://localhost:8080/api/concurrency-limit/demo", String.class);
                System.out.println(res);
                stop.countDown();
            });
            t[i].start();
        }
        start.countDown();
        stop.await();
        System.out.println("over");
    }
}
