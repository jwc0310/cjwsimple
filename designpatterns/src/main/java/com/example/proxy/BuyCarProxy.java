package com.example.proxy;

/**
 * Created by Administrator on 2017/11/13.
 */

public class BuyCarProxy implements IBuyCar {

    private Customer customer;

    public BuyCarProxy(Customer customer) {
        this.customer = customer;
    }


    @Override
    public void buyCar() {
        int cash = customer.getCash();
        if (cash<100000)
            System.out.println("cash is not enough ");
        customer.buyCar();
    }
}
