package com.github.xjs.exception;

import com.github.xjs.pojo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public R throwable(Throwable e){
        log.error(e.getMessage(), e);
        return R.error(e.getMessage());
    }
}
