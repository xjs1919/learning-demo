package com.github.xjs.actuator;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Base64;
import java.util.Objects;

public class ActuatorUtil {

	private static Logger logger = LoggerFactory.getLogger(ActuatorUtil.class.getName());

	public static boolean isBasicValid(String auth, ActuatorProperties properties){
		if((auth == null) || (auth.length() <= 6)){
			return false;
		}
		auth = auth.substring(6, auth.length());
		auth = new String(Base64.getDecoder().decode(auth));
		String authServer = properties.getUsername() + ":" + properties.getPassword();
		return Objects.equals(auth, authServer);
	}

	public static String getMetricsData(CollectorRegistry collectorRegistry){
		try {
			Writer writer = new StringWriter();
			TextFormat.write004(writer, collectorRegistry.metricFamilySamples());
			return writer.toString();
		} catch (IOException exception) {
			logger.error("获取监控数据异常:{}", getStackTrace(exception));
			return "";
		}
	}

	public static String getStackTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
			t.printStackTrace(printWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			return "Error while generating stack trace";
		}
	}
}
