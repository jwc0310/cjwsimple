package com.example.builder;

/**
 * Created by Administrator on 2017/11/13.
 */

public class Car {
    public final Engine engine;
    public final Tyre tyre;

    Car() {
        this(new Builder());
    }

    Car(final Engine engine, final Tyre tyre){
        this.engine = engine;
        this.tyre = tyre;
    }

    Car(Builder builder){
        this.engine = builder.engine;
        this.tyre = builder.tyre;
    }

    public void startDrive(){
        this.engine.startRun();
        this.tyre.startGo();
    }

    public static class Builder implements ICar {
        public Engine engine;
        public Tyre tyre;

        public Builder(Car car) {
            this.engine = car.engine;
            this.tyre = car.tyre;
        }

        public Builder() {
            this.engine = new Engine();
            this.tyre = new Tyre();
        }

        @Override
        public void buildEngine(int power) {
            this.engine = new Engine(power);
        }

        @Override
        public void buildTyre(int size) {
            this.tyre = new Tyre(size);
        }

        @Override
        public Car build() {
            return new Car(this);
        }
    }
}
