package com.think123.concurrency.example.Atomic;

import com.think123.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
/**
 * AtomicReferenceFileUpload
 * 必须有volatile修饰,不能有static修饰.
 *
 */
public class AtomicExample5 {

    public static AtomicIntegerFieldUpdater<AtomicExample5> updater=AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count=100;

    private static   AtomicExample5 atomicExample5=new AtomicExample5();

    public static void main(String[] args) {
        if(updater.compareAndSet(atomicExample5,100,120)){
            log.info("updater success{}"+atomicExample5.getCount());
        }
        if(updater.compareAndSet(atomicExample5,100,120)){
            log.info("updater success{}"+atomicExample5.getCount());
        }else{
            log.info("updater failed");
        }
    }
}