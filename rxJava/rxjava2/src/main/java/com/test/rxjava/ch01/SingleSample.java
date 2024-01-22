package com.test.rxjava.ch01;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 단, 1건만 통지한다.(????? 의미가 있나)
 */
public class SingleSample {
    public static void main(String[] args) {
        Single<DayOfWeek> single = Single.create(e -> e.onSuccess(LocalDate.now().getDayOfWeek()));
        single.subscribe(new SingleObserver<DayOfWeek>() {
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
        });
    }
}
