package com.test.reactive.ch14.generate;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class UsingExample {
    public static void main(String[] args) {
        Path path = Paths.get("파일 위치");

        // 파일 읽을때 참고하기
        Flux.using(() -> Files.lines(path), Flux::fromStream, Stream::close)
                .subscribe(log::info);
    }
}
