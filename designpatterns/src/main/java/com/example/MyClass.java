package com.example;

import com.example.template.Abstract;
import com.example.template.Concrete;

public class MyClass {

    public static void main(String[] args) {
        Abstract aa = new Concrete();
        aa.print("abc");
    }

}
