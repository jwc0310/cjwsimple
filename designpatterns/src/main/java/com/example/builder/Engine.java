package com.example.builder;

/**
 * Created by Administrator on 2017/11/13.
 */

final class Engine {

    private int power;

    public Engine(int power) {
        this.power = power;
    }

    public Engine() {
        this.power = 235;
    }

    public void startRun() {
        System.out.println("startRun startRun----->" + power);
    }
}
