package com.think123.concurrency.example.syncContainer;

import com.google.common.collect.Sets;
import com.think123.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class CollectionsExample2 {


    private static Set<Integer> set= Collections.synchronizedSet(Sets.newHashSet());

    //请求总数
    public static int clientTotal=5000;
    //线程总数
    public static int threadTotal=200;



    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);

        for(int i= 0; i<clientTotal;i++){
            final int count =i;
            executorService.execute(()->{

                try {
                    /**
                     * 引入信号量
                     * 先调用acquire方法,判断当前线程是否允许被执行
                     */
                    semaphore.acquire();
                    update(count);
                    /**
                     * 方法执行结束之后调用release方法
                     */
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
        log.info("size:{}",set.size());
    }

    private static void update(int i){
        set.add(i);
    }

}