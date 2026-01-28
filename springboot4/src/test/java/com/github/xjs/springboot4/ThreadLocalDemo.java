package com.github.xjs.springboot4;


public class ThreadLocalDemo {
    private static ThreadLocal<String> UserHolder = new ThreadLocal<>();
    public static void main(String[] args) throws Exception {
        for(int i=0; i< 10; i++){
            final int j = i;
            Thread.ofVirtual().name("worker"+j).start(() -> {
                UserHolder.set("value"+j);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() +":"+ UserHolder.get());
            });
        }
        System.in.read();
    }
}