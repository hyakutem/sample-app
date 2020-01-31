package com.example.sampleapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.sampleapp.handler.DelayHandler;

@Configuration
public class RoutesConfiguration {

	@Bean
	RouterFunction<ServerResponse> delayRoutes(DelayHandler handler) {
		return handler.routes();
	}

}
