package com.example.strategy;

/**
 * Created by Administrator on 2017/11/13.
 */

public class MyContext {

    public MyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    private Strategy strategy;

    public void contextInteface(){
        strategy.strategyInteface();
    }
}
