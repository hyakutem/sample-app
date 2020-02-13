package com.example.sampleapp.handler;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DelayHandler {

	/**
	 * Routing definition for this Handler
	 */
	public RouterFunction<ServerResponse> routes() {
		return RouterFunctions.route()
				.GET("/delay/{millis}", this::delay)
				.GET("/randomDelay/{maxMillis}", this::randomDelay)
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

	/**
	 * .GET("/randomDelay/{maxMillis}", this::randomDelay)
	 * 指定した[ms]をMaxとして、0~Maxのランダムな[ms]経過後に"OK"を返すだけのノンブロッキングなHandler
	 * 
	 * curl -v http://localhost:8080/randomDelay/3000
	 */
	public Mono<ServerResponse> randomDelay(ServerRequest request) {
		int maxMillis = Integer.parseInt(request.pathVariable("maxMillis"));
		int waitMillis = randomIntFor(maxMillis);
		log.debug("wait: " + waitMillis);
		return ServerResponse.ok().body(Mono.just("OK")
				.delayElement(Duration.ofMillis(waitMillis)), String.class);
	}

	// Refs: https://javamex.com/tutorials/random_numbers/xorshift.shtml#.XkUmSRP7TUo
	private static int randomIntFor(int max) {
		long x = System.nanoTime();
		x ^= (x << 21);
		x ^= (x >>> 35);
		x ^= (x << 4);
		return Math.abs((int) x % max);
	}

}
