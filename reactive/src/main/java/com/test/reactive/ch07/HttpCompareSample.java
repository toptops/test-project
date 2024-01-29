package com.test.reactive.ch07;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

public class HttpCompareSample {
    public static void main(String[] args) throws InterruptedException {
        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();


        Mono<String> mono = getWorldTime(worldTimeUri).cache(); // hot
//        Mono<String> mono = getWorldTime(worldTimeUri); // cold
        mono.subscribe(s -> System.out.println("Subscribe1: " + s));
        Thread.sleep(2000L);
        mono.subscribe(s -> System.out.println("Subscribe2: " + s));
        Thread.sleep(2000L);
    }

    private static Mono<String> getWorldTime(URI worldTimeUri) {
        return WebClient.create()
                .get()
                .uri(worldTimeUri)
                .retrieve()
                .bodyToMono(String.class);
    }
}
