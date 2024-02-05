package com.test.reactive.webFlux.core;

import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public class BookRouterFunctionFilter implements HandlerFilterFunction {
    @Override
    public Mono filter(ServerRequest request, HandlerFunction next) {
        String path = request.requestPath().value();
        return next.handle(request).doAfterTerminate(() -> {
            System.out.println("------------");
            System.out.println("HandlerFilter Book Log...!");
            System.out.println("Path: " + path + ", status: " + request.exchange().getResponse().getStatusCode());
            System.out.println("------------");
        });
    }
}
