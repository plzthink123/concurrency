package com.think123.concurrency.singleton;

import com.think123.concurrency.annoations.ThreadSafe;

/**
 * 懒汉  双重同步锁单例模式
 */
@ThreadSafe
public class SingleExample5 {
    private SingleExample5(){

    }

    /*
    通过volatile关键字限制指令重排后,该单例模式线程安全
     */
    private  volatile static SingleExample5 instance=null;

    //static 工厂方法

   /*
    instance=new SingleExample4();这句话在内存中的指令如下:

    1.memory=allocate()  分配对象的内存空间
    2.ctorInstance()     初始化对象
    3.instance=memory    设置instance指向刚分配的内存

        JVM和CPU优化:发生了指令重排序:
        加入发生如下:
                1.memory=allocate()  分配对象的内存空间
                3.instance=memory    设置instance指向刚分配的内存
                2.ctorInstance()     初始化对象
    */
    public static SingleExample5 getInstance(){
        if(instance==null){//双重检测机制+锁🔐     //A
            synchronized (SingleExample1.class){
                if(instance==null)
                instance=new SingleExample5();  //B
            }
        }
        return instance;
    }
}