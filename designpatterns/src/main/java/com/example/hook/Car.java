package com.example.hook;

/**
 * Created by Administrator on 2017/11/22.
 */

public class Car {

    private CarEngineInterface carEngine;

    public Car() {
        carEngine = new CarEngine();
    }

    public void showMaxSpeed() {
        System.out.println("my max speed is "+carEngine.maxSpeed());
    }
}
