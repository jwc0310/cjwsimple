package com.example.builder;

/**
 * Created by Administrator on 2017/11/13.
 */

public interface ICar {

    void buildEngine(int power);
    void buildTyre(int size);
    Car build();

}
