package com.think123.concurrency.singleton;

import com.think123.concurrency.annoations.Recommend;
import com.think123.concurrency.annoations.ThreadSafe;

/**
 * 枚举
 */
@ThreadSafe
@Recommend
public class SingleExample7 {
   private SingleExample7(){

   }
   public  static SingleExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
   }

   private enum Singleton{
       INSTANCE;

       private SingleExample7 single;

       //JVM来保证此方法绝对只被调用一次
         Singleton(){
           single=new SingleExample7();
       }

       public SingleExample7 getInstance(){
             return single;
       }
   }

}