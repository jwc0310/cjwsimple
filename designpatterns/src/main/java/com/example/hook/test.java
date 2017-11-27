package com.example.hook;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/11/22.
 */

public class test {

    public static void main(String[] args) {
        Car car = new Car();
        car.showMaxSpeed();

        Car car1 = new Car();
        car1.showMaxSpeed();
        try {
            Field carEngineFiled = Car.class.getDeclaredField("carEngine");
            carEngineFiled.setAccessible(true);
            CarEngine carEngine1 = (CarEngine)carEngineFiled.get(car1);
            carEngineFiled.set(car1, new EvilCarEngine(carEngine1));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        car1.showMaxSpeed();


        Car car2 = new Car();
        car2.showMaxSpeed();

        try {
            Field field = Car.class.getDeclaredField("carEngine");
            field.setAccessible(true);
            CarEngine carEngine2 = (CarEngine) field.get(car2);

            CarEngineInterface carEngineInterface = (CarEngineInterface) Proxy.newProxyInstance(CarEngine.class.getClassLoader()
            , new Class[]{CarEngineInterface.class},
                    new CarEngineProxyHandler(carEngine2));

            field.set(car2, carEngineInterface);

        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        car2.showMaxSpeed();

    }

}
