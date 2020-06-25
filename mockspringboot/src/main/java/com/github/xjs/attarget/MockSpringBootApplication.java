package com.github.xjs.attarget;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class MockSpringBootApplication {

    public static void main(String[] args) throws Exception{
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(8080);
        tomcat.getService().addConnector(connector);
        tomcat.addWebapp("", "e:/tomcat");
        tomcat.start();
    }
}
