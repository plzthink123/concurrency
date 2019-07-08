package com.think123.concurrency.example.commonUnsafe;

import com.think123.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
simpleDateFormat
JodaTime
 */
@Slf4j
@ThreadSafe
/*
  堆栈封闭导致线程安全
 */
public class DateFormatExample2 {





    //请求总数
    public static int clientTotal=5000;
    //线程总数
    public static int threadTotal=200;

    public static StringBuilder stringBuilder=new StringBuilder();


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
                    update();
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
    }

    private static void update(){
        try {
               SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20190708");
        } catch (ParseException e) {
            log.error("parse Exception",e);
            e.printStackTrace();
        }
    }

}