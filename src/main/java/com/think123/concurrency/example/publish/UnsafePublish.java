package com.think123.concurrency.example.publish;

import com.think123.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@NotThreadSafe
@Slf4j
public class UnsafePublish {
    private String[] states ={"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish=new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
        unsafePublish.getStates()[0]="d";
        log.info("{}",Arrays.toString(unsafePublish.getStates()));
    }
}