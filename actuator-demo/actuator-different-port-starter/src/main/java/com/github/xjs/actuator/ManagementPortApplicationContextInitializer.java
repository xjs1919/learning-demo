package com.github.xjs.actuator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ManagementPortApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    private static final String SERVER_PORT = "server.port";
    private static final String MANAGEMENT_SERVER_PORT = "management.server.port";
    private static final String MANAGEMENT_PORT_OFFSET = "management.port.offset";
    private static final String MANAGEMENT_PROPERTY = "managementProperties";

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        ApplicationContext parent = context.getParent();
        if(parent == null){
            return;
        }
        ConfigurableEnvironment environment = context.getEnvironment();
        String managementPort = environment.getProperty(MANAGEMENT_SERVER_PORT);
        // 说明已经配置了
        if(StringUtils.hasText(managementPort) && managementPort.matches("\\d+")){
            return;
        }
        // 获取server.port
        String serverPort = environment.getProperty(SERVER_PORT);
        // 获取management.port.offset
        Integer portOffset = environment.getProperty(MANAGEMENT_PORT_OFFSET, Integer.class);
        if(!StringUtils.hasText(serverPort)){
            serverPort = "8080";
        }
        if(portOffset == null){
            portOffset = 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(MANAGEMENT_SERVER_PORT, Integer.parseInt(serverPort) + portOffset);
        MapPropertySource mapPropertySource = new MapPropertySource(MANAGEMENT_PROPERTY, map);
        environment.getPropertySources().addLast(mapPropertySource);
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
