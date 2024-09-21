package com.github.xjs.actuator;

import io.prometheus.client.CollectorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public class ActuatorWebController implements HandlerFunction {

    private final ActuatorProperties properties;

    private final CollectorRegistry collectorRegistry;

	@Autowired
	public ActuatorWebController(ActuatorProperties properties, CollectorRegistry collectorRegistry){
		this.collectorRegistry = collectorRegistry;
		this.properties = properties;
	}

	@Override
	public ServerResponse handle(ServerRequest serverRequest) throws Exception {
		if(!properties.getEnable()){
			return ServerResponse.status(HttpStatus.NOT_FOUND).build();
		}
		String auth = serverRequest.headers().firstHeader("Authorization");
		if (ActuatorUtil.isBasicValid(auth, properties)) {
			String content = ActuatorUtil.getMetricsData(collectorRegistry);
			return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(content);
		} else {
			return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
