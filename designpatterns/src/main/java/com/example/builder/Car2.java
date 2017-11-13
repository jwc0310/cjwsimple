package com.example.builder;

/**
 * Created by Administrator on 2017/11/13.
 */

public class Car2 {
    private int power;
    private int size;

    private Car2(int power, int size) {
        this.power = power;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Car2{" +
                "power=" + power +
                ", size=" + size +
                '}';
    }

    public static class CarBuilder {
        private int power;
        private int size;

        public CarBuilder() {
            this.power = 11;
            this.size = 12;
        }

        public CarBuilder power(int power) {
            this.power = power;
            return this;
        }

        public CarBuilder size(int size) {
            this.size = size;
            return this;
        }

        public Car2 build() {
            return new Car2(power, size);
        }
    }

    public static void main(String[] args) {
        Car2.CarBuilder carBuilder = new CarBuilder();
        Car2 car2 = carBuilder.power(1212)
                .size(2222)
                .build();
        System.out.println(car2.toString());
    }


}
