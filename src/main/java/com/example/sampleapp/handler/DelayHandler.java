package com.example.sampleapp.handler;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class DelayHandler {

	/**
	 * Routing definition for this Handler
	 */
	public RouterFunction<ServerResponse> routes() {
		return RouterFunctions.route()
				.GET("/delay/{millis}", this::delay)
				.build();
	}

	/**
	 * .GET("/delay/{millis}", this::delay)
	 * 指定した[ms]経過後に"OK"を返すだけのノンブロッキングなHandler
	 * 
	 * curl -v http://localhost:8080/delay/500
	 */
	public Mono<ServerResponse> delay(ServerRequest request) {
		int millis = Integer.parseInt(request.pathVariable("millis"));
		return ServerResponse.ok().body(Mono.just("OK").delayElement(Duration.ofMillis(millis)), String.class);
	}

}
