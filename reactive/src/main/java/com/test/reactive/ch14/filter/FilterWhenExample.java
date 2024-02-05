package com.test.reactive.ch14.filter;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.util.Map;

@Slf4j
public class FilterWhenExample {
    public static void main(String[] args) throws InterruptedException {
        Map<SampleData.CovidVaccine, Tuple2<SampleData.CovidVaccine, Integer>> vaccineMap = SampleData.getCovidVaccines();

        Flux.fromIterable(SampleData.coronaVaccineNames)
                .filterWhen(v -> Mono
                        .just(vaccineMap.get(v).getT2() >= 3_000_000)
                        .publishOn(Schedulers.parallel()))
                .subscribe(d -> log.info("# onNext: {}", d));
        Thread.sleep(1000L);
    }
}
