package com.example.proxy;

/**
 * Created by Administrator on 2017/11/13.
 */

public class Customer implements IBuyCar {

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    private int cash;

    @Override
    public void buyCar() {
        System.out.println("cost "+cash);
    }
}
