package com.example.demo.log;

import ch.qos.logback.classic.PatternLayout;

/**
 * @author xujiashuai
 * @date 2022/5/27 14:46
 **/
public class UserLogbackLayout extends PatternLayout {

    static {
        DEFAULT_CONVERTER_MAP.put("u", LogbackUserPatternConverter.class.getName());
    }
}
