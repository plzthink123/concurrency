package com.think123.concurrency.singleton;

import com.think123.concurrency.annoations.NotRecommend;
import com.think123.concurrency.annoations.ThreadSafe;

/**
 * 懒汉
 */
@ThreadSafe
@NotRecommend
public class SingleExample3 {
    private SingleExample3(){

    }

    private static SingleExample3 instance=null;

    //static 工厂方法
    public static synchronized  SingleExample3 getInstance(){
        if(instance==null){
            instance=new SingleExample3();
        }
        return instance;
    }
}