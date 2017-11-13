package com.example.builder;

/**
 * Created by Administrator on 2017/11/13.
 */

public class Tyre {
    private int size;

    public Tyre(int size) {
        this.size = size;
    }

    public Tyre() {
        this.size = 18;
    }

    public void startGo() {
        System.out.println("startRun startGo---->" + size);
    }
}
