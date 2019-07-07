package com.think123.concurrency.singleton;

import com.think123.concurrency.annoations.ThreadSafe;

/**
 * 饿汉
 */
@ThreadSafe
public class SingleExample2 {

    private SingleExample2(){

    }

    private static SingleExample2 instance=new SingleExample2();

    //static 工厂方法
    public static SingleExample2 getInstance(){

        return instance;
    }
}