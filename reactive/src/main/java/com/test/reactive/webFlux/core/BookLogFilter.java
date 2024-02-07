package com.test.reactive.webFlux.core;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class BookLogFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        return chain.filter(exchange).doAfterTerminate(() -> {
            if(path.contains("books")) {
                System.out.println("------------");
                System.out.println("WebFilter Book Log...!");
                System.out.println("Path: " + path + ", status: " + exchange.getResponse().getStatusCode());
                System.out.println("------------");
            }
        });
    }
}