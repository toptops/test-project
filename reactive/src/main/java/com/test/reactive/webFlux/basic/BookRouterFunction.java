package com.test.reactive.webFlux.basic;

import com.test.reactive.webFlux.core.BookDto;
import com.test.reactive.webFlux.core.BookRouterFunctionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class BookRouterFunction {

    /**
     * 음 오묘하군..? 이렇게도 쓸 수 있구나.. 뭔가 되게 퓨어한 자바 같다.
     *
     * @return
     */
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions
                .route(GET("/v1/router/books/{book-id}"),
                        (ServerRequest request) -> this.getBook(request))
                .filter(new BookRouterFunctionFilter());
    }

    private Mono<ServerResponse> getBook(ServerRequest request) {
        return ServerResponse.ok()
                .body(Mono.just(BookDto.ResponseV1.builder()
                        .bookId(Long.parseLong(request.pathVariable("book-id")))
                        .bookName("Advanced Reactor")
                        .author("Tom")
                        .isbn("222-22-2222-222-2")
                        .build()), BookDto.ResponseV1.class);
    }
}
