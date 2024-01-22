package com.test.rxjava.ch01;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 1건만 통지하거나 통지하지 않거나..
 */
public class MaybeSample {

    public static void main(String[] args) {
//        Maybe<DayOfWeek> maybe = Maybe.create(e -> e.onSuccess(LocalDate.now().getDayOfWeek()));
        Maybe<DayOfWeek> maybe = Maybe.empty(); // ??????? 테스트 용도인가 보다!
        maybe.subscribe(new MaybeObserver<DayOfWeek>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull DayOfWeek dayOfWeek) {
                System.out.println(dayOfWeek);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("에러="+e);
            }

            @Override
            public void onComplete() {
                System.out.println("완료");
            }
        });
    }
}
