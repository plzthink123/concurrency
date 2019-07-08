package com.think123.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

public class VectorExample3 {


    //java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1){  //foreach
        for(Integer i: v1){
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v2){ //iterator
        Iterator<Integer> iterator=v2.iterator();
        while(iterator.hasNext()){
            Integer i =iterator.next();
            if(i==3){
                v2.remove(i);
            }
        }
    }

    //success
    private static void test3(Vector<Integer> v3){
        for(int i=0;i<v3.size();i++){
            if(i==3){
                v3.remove(i);
            }
        }
    }


    public static void main(String[] args) {
          Vector<Integer> vector=new Vector<>();
          vector.add(1);
          vector.add(2);
          vector.add(3);
          test1(vector);
    }
}