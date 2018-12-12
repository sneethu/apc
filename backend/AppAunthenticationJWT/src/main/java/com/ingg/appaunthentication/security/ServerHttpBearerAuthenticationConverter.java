/*package com.ingg.appaunthentication.security;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerHttpBasicAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class ServerHttpBearerAuthenticationConverter extends ServerHttpBasicAuthenticationConverter {

	@Override
	 public Mono<Authentication> convert(ServerWebExchange serverWebExchange) {

		return Mono.justOrEmpty(serverWebExchange).
                .flatMap(authorizationHeaderPayload::extract)
                   .filter(matchBearerLength)
                .flatMap(isolateBearerValue)
                .flatMap(jwtVerifier::check)
                .flatMap(UsernamePasswordAuthenticationBearer::create).log();
    }
}
*/