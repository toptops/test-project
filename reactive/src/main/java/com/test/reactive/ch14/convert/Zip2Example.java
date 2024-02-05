package com.test.reactive.ch14.convert;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

@Slf4j
public class Zip2Example {
    public static void main(String[] args) {
        getInfectedPersonPerHour(10, 21)
                .subscribe( tuples -> {
                    Tuple3<Tuple2, Tuple2, Tuple2> t3 = (Tuple3) tuples;
                    int sum = (int)t3.getT1().getT2() + (int) t3.getT2().getT2() + (int) t3.getT3().getT2();
                    log.info("# onNext : {}, {}", t3.getT1().getT1(), sum);
                });
    }

    private static Flux getInfectedPersonPerHour(int start, int end) {
        return Flux.zip(
                Flux.fromIterable(SampleData.seoulInfected).filter(t2 -> t2.getT1() >= start && t2.getT1() <= end),
                Flux.fromIterable(SampleData.incheonInfected).filter(t2 -> t2.getT1() >= start && t2.getT1() <= end),
                Flux.fromIterable(SampleData.suwonInfected).filter(t2 -> t2.getT1() >= start && t2.getT1() <= end)
        );
    }
}
