package com.test.reactive.webFlux.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration("bookRouterV1")
public class BookRouter {

    @Bean
    public RouterFunction<?> routeBook(BookHandler bookHandler) {
        return route()
                .POST("/v1/router/books", bookHandler::createBook)
                .PATCH("/v1/router/books", bookHandler::updateBook)
                .GET("/v1/router/books", bookHandler::getBooks)
                .GET("/v1/router/books/{book-id}", bookHandler::getBook)
                .build();
    }
}
