package com.test.reactive.ch06;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;

public class MonoExample {
    public static void main(String[] args) {
        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Mono.just(restTemplate.exchange(worldTimeUri, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class))
                .map(res -> res.getBody())
                .subscribe(
                        d -> System.out.println(d),
                        error -> System.out.println(error),
                        () -> System.out.println("complete")
                );
    }
}
