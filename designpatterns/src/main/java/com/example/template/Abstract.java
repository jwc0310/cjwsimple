package com.example.template;

/**
 * 模板方法模式是类的行为模式
 * Created by Administrator on 2017/11/13.
 */

public abstract class Abstract {

    /**
     * 公共的方法
     **/
    public final void print(String url) {
        System.out.println(setFormatUrl(url));
    }

    /**
     * 自定义方法
     **/
    protected abstract String setFormatUrl(String url);

}
