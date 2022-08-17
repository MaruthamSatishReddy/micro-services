package com.easytobuy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class APIGateWayFilter implements GlobalFilter {
Logger logger=LoggerFactory.getLogger(APIGateWayFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		org.springframework.http.server.reactive.ServerHttpRequest httpRequest=exchange.getRequest();
		logger.info("Authoriation"+httpRequest.getHeaders().getFirst("Authorization"));
		return chain.filter(exchange).then(Mono.fromRunnable(() ->{
			ServerHttpResponse response=exchange.getResponse();
			logger.info("Response:::"+response.getStatusCode());
		}
		));
	}

}
