package com.github.xjs.springboot4;


import java.time.LocalDateTime;

public class PinningDetector {
    public static void main(String[] args) throws Exception {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "8");
        System.setProperty("jdk.tracePinnedThreads", "full");
        System.out.println(LocalDateTime.now()+" start");
        for(int i=0;i<8;i++){
            Thread.startVirtualThread(() -> {
                synchronized (new Object()) {
                    try {
                        Thread.sleep(10000);
                    } catch (Exception e) {}
                }
            });
        }
        Thread.sleep(1000);
        Thread.startVirtualThread(()-> System.out.println(LocalDateTime.now() + " end"));
        System.in.read();
    }
}