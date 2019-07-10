package com.think123.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample1 {

    private static  CyclicBarrier cyclicBarrier=new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec= Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            final int threadNum=i;

                Thread.sleep(1000);
            exec.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("{}",e);
                }
            });

        }
    }

    private static void race(int threadNum) throws  Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        cyclicBarrier.await();
        log.info("{} continue",threadNum);
    }
}