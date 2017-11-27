package com.example.hook;

/**
 * Created by Administrator on 2017/11/22.
 */

public class EvilCarEngine extends CarEngine {

    private CarEngineInterface base;

    public EvilCarEngine(CarEngineInterface carEngineInterface) {
        this.base = carEngineInterface;
    }

    public int maxSpeed() {
        return 3 * base.maxSpeed();
    }

}
