package com.think123.concurrency.example.Atomic;

import com.think123.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
/**
 * LongAdder     JDK8新增的一个类,
 *              核心思想:
 *                  将热点数据分离
 *                 低并发:
 *                 高并发:热点分离,实现均衡
 *              缺点:  在统计的时候,如果有并发更新,则会有数据误差
 *
 *              在高并发情况下,尽量使用LongAdder,减少使用Atomic
 */
public class AtomicExample3 {
    //请求总数
    public static int clientTotal=5000;
    //线程总数
    public static int threadTotal=200;

    public static LongAdder count = new LongAdder();


    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);

        for(int i= 0; i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    /**
                     * 引入信号量
                     * 先调用acquire方法,判断当前线程是否允许被执行
                     */
                    semaphore.acquire();
                    add();
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
        log.info("count{}",count);
    }

    private static void add(){
        count.increment();
       // count.getAndIncrement();
    }
}