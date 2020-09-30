package com.github.xjs.simple.apollo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 15:00
 */
public class DefaultApplicationProvider {

    private static final Logger logger = LoggerFactory.getLogger(DefaultApplicationProvider.class);
    public static final String APP_PROPERTIES_CLASSPATH = "/META-INF/app.properties";
    private Properties m_appProperties = new Properties();
    private String m_appId;
    private String m_meta;

    private static DefaultApplicationProvider defaultApplicationProvider = new DefaultApplicationProvider();

    private DefaultApplicationProvider(){
        initialize();
    }

    public static DefaultApplicationProvider getInstance(){
        return defaultApplicationProvider;
    }

    public void initialize() {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(APP_PROPERTIES_CLASSPATH.substring(1));
            if (in == null) {
                in = DefaultApplicationProvider.class.getResourceAsStream(APP_PROPERTIES_CLASSPATH);
            }
            initialize(in);
        } catch (Throwable ex) {
            logger.error("Initialize DefaultApplicationProvider failed.", ex);
        }
    }

    public void initialize(InputStream in) {
        try {
            if (in != null) {
                try {
                    this.m_appProperties
                            .load(new InputStreamReader(in, StandardCharsets.UTF_8));
                } finally {
                    in.close();
                }
            }
            this.m_appId = m_appProperties.getProperty("appId");
            this.m_meta = m_appProperties.getProperty("meta");
        } catch (Throwable ex) {
            logger.error("Initialize DefaultApplicationProvider failed.", ex);
        }
    }

    public String getAppId() {
        return m_appId;
    }

    public String getMeta(){
        return this.m_meta;
    }
}
