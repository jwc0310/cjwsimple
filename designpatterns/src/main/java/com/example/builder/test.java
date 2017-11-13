package com.example.builder;

/**
 * build方式有很多， 这只是中一种
 * Created by Administrator on 2017/11/13.
 */

public class test {

    //马力为300，轮胎尺寸为18的suv
    public static void main(String[] args){
        Car.Builder builder = new Car.Builder();
        builder.buildEngine(120);
        builder.buildTyre(120);
        Car car = builder.build();
        car.startDrive();
    }
}
