package com.github.xjs.actuator;

import io.prometheus.client.CollectorRegistry;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.reactive.WebFluxEndpointManagementContextConfiguration;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.servlet.WebMvcEndpointManagementContextConfiguration;
import org.springframework.boot.actuate.endpoint.web.reactive.WebFluxEndpointHandlerMapping;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@EnableConfigurationProperties(ActuatorProperties.class)
@ConditionalOnProperty(prefix = "management.prometheus", name="enable", havingValue = "true", matchIfMissing = true)
public class ActuatorConfiguration {

	@Configuration
	@AutoConfigureBefore(WebMvcEndpointManagementContextConfiguration.class)
	@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
	public static class PrometheusWebConfiguration{

		@Bean
		public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping() {
			return new WebMvcEndpointHandlerMapping(null, new ArrayList<>(), null, null, null, false);
		}

		@Bean
		public org.springframework.web.servlet.function.RouterFunction<?> routeFunction(ActuatorProperties properties, CollectorRegistry collectorRegistry){
			return org.springframework.web.servlet.function.RouterFunctions.route(
				org.springframework.web.servlet.function.RequestPredicates.GET("/prometheus/metrics"),
				new ActuatorWebController(properties, collectorRegistry));
		}
	}

	@Configuration
	@AutoConfigureBefore(WebFluxEndpointManagementContextConfiguration.class)
	@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
	public static class PrometheusWebfluxConfiguration{

		@Bean
		public WebFluxEndpointHandlerMapping webEndpointReactiveHandlerMapping() {
			return new WebFluxEndpointHandlerMapping(null, new ArrayList<>(), null, null, null, false);
		}

		@Bean
		public org.springframework.web.reactive.function.server.RouterFunction<?> routeFunction(ActuatorProperties properties, CollectorRegistry collectorRegistry){
			return org.springframework.web.reactive.function.server.RouterFunctions.route(
				org.springframework.web.reactive.function.server.RequestPredicates.GET("/prometheus/metrics"),
				new ActuatorWebfluxController(properties, collectorRegistry));
		}
	}

}
