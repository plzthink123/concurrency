package com.think123.concurrency.example.singleton;

import com.think123.concurrency.annoations.NotThreadSafe;

/**
 * 饿汉
 */
@NotThreadSafe
public class SingleExample6 {
    private SingleExample6(){

    }
    private static SingleExample6 instance=null;

    static{
        instance=new SingleExample6();
    }


    //static 工厂方法
    public static SingleExample6 getInstance(){

        return instance;
    }

    public static void main(String[] args) {
        System.out.println(instance.hashCode());
        System.out.println(instance.hashCode());
    }
}