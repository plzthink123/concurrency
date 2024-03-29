package com.think123.concurrency.example.lock;

import com.think123.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

@Slf4j
@ThreadSafe
public class LockExample5 {
    //请求总数
    public static int clientTotal=5000;
    //线程总数
    public static int threadTotal=200;

    public static int count =0;

    public  static final StampedLock lock=new StampedLock();


    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);

        for(int i= 0; i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    //记录异常
                    log.error("expection",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count{}",count);
    }

    private  static void add(){
        long stamp = lock.writeLock();
        try {
            count++;
        }finally{
            lock.unlock(stamp);
        }
    }
}