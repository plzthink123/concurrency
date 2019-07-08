package com.think123.concurrency.example.singleton;

import com.think123.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉
 */
@NotThreadSafe
public class SingleExample1 {
    private SingleExample1(){

    }

    private static  SingleExample1 instance=null;

    //static 工厂方法
    public static  SingleExample1 getInstance(){
        if(instance==null){
            instance=new SingleExample1();
        }
        return instance;
    }
}