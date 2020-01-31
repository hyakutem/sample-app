package com.example.sampleapp.handler;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Component
@ConfigurationProperties(prefix = "app")
public class IndexHandler {

	@Setter
	@Getter
	private String title = "sample-app";

	public RouterFunction<ServerResponse> routes() {
		return RouterFunctions.route()
				.GET("/", this::index)
				.build();
	}

	public Mono<ServerResponse> index(ServerRequest request) {
		return ServerResponse.ok().bodyValue(title);
	}

}
