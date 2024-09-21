package com.github.xjs.actuator;

import io.prometheus.client.CollectorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ActuatorWebfluxController implements HandlerFunction {

    private ActuatorProperties properties;

    private CollectorRegistry collectorRegistry;

	@Autowired
	public ActuatorWebfluxController(ActuatorProperties properties, CollectorRegistry collectorRegistry){
		this.collectorRegistry = collectorRegistry;
		this.properties = properties;
	}

	@Override
	public Mono<ServerResponse> handle(ServerRequest serverRequest) {
		if(!properties.getEnable()){
			return ServerResponse.status(HttpStatus.NOT_FOUND).build();
		}
		String auth = serverRequest.headers().firstHeader("Authorization");
		if (ActuatorUtil.isBasicValid(auth, properties)) {
			String content = ActuatorUtil.getMetricsData(collectorRegistry);
			return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just(content), String.class);
		} else {
			return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
		}
	}


}
