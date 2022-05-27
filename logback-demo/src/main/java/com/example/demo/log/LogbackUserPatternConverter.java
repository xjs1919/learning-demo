package com.example.demo.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.example.demo.user.UserHolder;

/**
 * @author xujiashuai
 * @date 2022/5/27 14:49
 **/
public class LogbackUserPatternConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return UserHolder.get();
    }

}
