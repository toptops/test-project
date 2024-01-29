package com.test.reactive.ch01;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sub/v1/coffee")
public class SubCoffeeController {
    private Map<Long, Coffee> coffeeMap;

    public SubCoffeeController() {
        this.coffeeMap = new HashMap<>();
        Coffee coffee1 = new Coffee(1, "Test1");
        Coffee coffee2 = new Coffee(2, "Test2");
        Coffee coffee3 = new Coffee(3, "Test3");

        this.coffeeMap.put(1L, coffee1);
        this.coffeeMap.put(2L, coffee2);
        this.coffeeMap.put(3L, coffee3);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/blocking/{id}")
    public ResponseEntity<Coffee> getBlockingCoffee(@PathVariable("id") long id) throws InterruptedException {
        Thread.sleep(5000L);
        Coffee coffee = coffeeMap.get(id);
        return ResponseEntity.ok(coffee);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/non-blocking/{id}")
    public Mono<Coffee> getNonBlockingCoffee(@PathVariable("id") long id) throws InterruptedException {
        Thread.sleep(5000L);
        Coffee coffee = coffeeMap.get(id);
        return Mono.just(coffee);
    }
}

