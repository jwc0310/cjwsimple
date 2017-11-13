package com.example.strategy;

/**
 * Created by Administrator on 2017/11/13.
 */

public class test {

    public static void main(String[] args){
        Strategy strategy = new ConcreteStrategyC();
        MyContext myContext = new MyContext(strategy);
        myContext.contextInteface();
    }

}
