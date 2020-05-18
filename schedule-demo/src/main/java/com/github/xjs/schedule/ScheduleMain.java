package com.github.xjs.schedule;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Future;

public class ScheduleMain {
    public static void main(String[] args)throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ScheduleApp.class);
        ScheduleBean bean = ctx.getBean(ScheduleBean.class);
        /**
         * 11:35:58.191 [pool-1-thread-1] INFO com.github.xjs.schedule.ScheduleBean - pool-1-thread-1,fixedRate
         * 11:36:00.004 [pool-1-thread-1] INFO com.github.xjs.schedule.ScheduleBean - pool-1-thread-1,cron
         * 11:36:01.191 [pool-1-thread-1] INFO com.github.xjs.schedule.ScheduleBean - pool-1-thread-1,fixedRate
         * 11:36:04.192 [pool-1-thread-1] INFO com.github.xjs.schedule.ScheduleBean - pool-1-thread-1,fixedRate
         * */
    }
}
