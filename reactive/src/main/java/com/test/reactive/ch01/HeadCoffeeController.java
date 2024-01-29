package com.test.reactive.ch01;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@RestController
@RequestMapping("/head/v1/coffee")
@RequiredArgsConstructor
public class HeadCoffeeController {
    URI subBaeUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("localhost")
            .port(8080)
            .path("/sub/v1/coffee")
            .build()
            .encode()
            .toUri();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/blocking/{id}")
    public ResponseEntity<Coffee> getBlockingCoffee(@PathVariable("id") long id) {
        URI getCoffeUri = UriComponentsBuilder.fromUri(subBaeUri)
                .path("/blocking/{id}")
                .build()
                .expand(id)
                .encode()
                .toUri();

        ResponseEntity<Coffee> response = getRestTemplate().getForEntity(getCoffeUri, Coffee.class);
        Coffee coffee = response.getBody();
        return ResponseEntity.ok(coffee);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/blocking")
    public ResponseEntity<String> getBlockingCoffeeAll() {
        for(int i=1; i<=3; i++) {
            URI getCoffeUri = UriComponentsBuilder.fromUri(subBaeUri)
                    .path("/blocking/{id}")
                    .build()
                    .expand(i)
                    .encode()
                    .toUri();

            ResponseEntity<Coffee> response = getRestTemplate().getForEntity(getCoffeUri, Coffee.class);
            Coffee coffee = response.getBody();
            System.out.println(coffee);
        }

        return ResponseEntity.ok("OK");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/non-blocking/{id}")
    public Mono<Coffee> getNonBlockingCoffee(@PathVariable("id") long id) {
        URI getCoffeUri = UriComponentsBuilder.fromUri(subBaeUri)
                .path("/non-blocking/{id}")
                .build()
                .expand(id)
                .encode()
                .toUri();

        return WebClient.create()
                .get()
                .uri(getCoffeUri)
                .retrieve()
                .bodyToMono(Coffee.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/non-blocking")
    public ResponseEntity<String> getNonBlockingCoffeeAll() throws InterruptedException {
        for(int i=1; i<=3; i++) {
            URI getCoffeUri = UriComponentsBuilder.fromUri(subBaeUri)
                    .path("/non-blocking/{id}")
                    .build()
                    .expand(i)
                    .encode()
                    .toUri();
            Mono<Coffee> coffee = WebClient.create()
                    .get()
                    .uri(getCoffeUri)
                    .retrieve()
                    .bodyToMono(Coffee.class);
            coffee.subscribe(c -> System.out.println(c));
        }

        Thread.sleep(6000L);


        return ResponseEntity.ok("OK");
    }

    private RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().build();
    }
}
