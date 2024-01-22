package com.test.rxjava.ch02;

import io.reactivex.functions.Action;

/**
 * 람다식과 익명클래스 This 비교
 */
public class DifferenceOfThisSample {
    public static void main(String[] args) throws Exception {
        DifferenceOfThisSample sample = new DifferenceOfThisSample();
        sample.execute();
    }

    public void execute() throws Exception {
        Action anonymous  = new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("익명 클래스의 this: " + this);
            }

            @Override
            public String toString() {
                return "?";
            }
        };

        Action lambda = () -> System.out.println("람다식의 this: " + this);
        anonymous.run();
        lambda.run();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
