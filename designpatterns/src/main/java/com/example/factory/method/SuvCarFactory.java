package com.example.factory.method;

import com.example.factory.simple.Car;
import com.example.factory.simple.SuvCar;

/**
 * Created by Administrator on 2017/11/13.
 */

public class SuvCarFactory implements ICardFactory {
    @Override
    public Car createCar() {
        return new SuvCar();
    }
}
