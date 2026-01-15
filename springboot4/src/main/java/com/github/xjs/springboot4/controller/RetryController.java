package com.github.xjs.springboot4.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@EnableResilientMethods
@RestController
@RequestMapping("api/retry/")
public class RetryController {

    private static Logger log = LoggerFactory.getLogger(RetryController.class);

    /**
     * 2026-01-14T14:10:45.706+08:00  INFO 4572 --- [springboot4] [nio-8080-exec-1] c.g.x.s.controller.RetryController       : RetryController demo
     * 2026-01-14T14:10:46.719+08:00  INFO 4572 --- [springboot4] [nio-8080-exec-1] c.g.x.s.controller.RetryController       : RetryController demo
     * 2026-01-14T14:10:47.725+08:00  INFO 4572 --- [springboot4] [nio-8080-exec-1] c.g.x.s.controller.RetryController       : RetryController demo
     * 2026-01-14T14:10:48.737+08:00  INFO 4572 --- [springboot4] [nio-8080-exec-1] c.g.x.s.controller.RetryController       : RetryController demo
     * 2026-01-14T14:10:48.744+08:00 ERROR 4572 --- [springboot4] [nio-8080-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: java.lang.ArithmeticException: / by zero] with root cause
     * */
    @Retryable
    @GetMapping(path = "/demo")
    public String demo(){
        log.info("RetryController demo");
        int a = 1/0;
        return "demo";
    }

}
