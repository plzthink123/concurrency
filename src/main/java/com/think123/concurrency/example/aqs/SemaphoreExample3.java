package com.think123.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample3 {

    private final static int threadCount=20;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec= Executors.newCachedThreadPool();

         final Semaphore semaphore=new Semaphore(3);

        for(int i=0;i<threadCount;i++){
            final int threadNum=i;
            exec.execute(()->{
                try {
                    if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {   //尝试获得许可,如果得到,则;得不到,则丢弃
                       //semaphore.acquire(3);//获取多个许可
                        test(threadNum);
                        semaphore.release();//释放多个许可
                    }
                } catch (InterruptedException e) {
                   log.info("{}",e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}