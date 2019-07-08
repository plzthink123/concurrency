package com.think123.concurrency.example.immutable;


import com.google.common.collect.Maps;
import com.think123.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private static final int a=1;

    private static final String b="2";

    private static final Map<Integer,Integer> map =Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
//        map= Maps.newHashMap();
//        a=2;
//        b="3123";

        map.put(1,3);
        log.info("{}",map.get(1));
    }
}