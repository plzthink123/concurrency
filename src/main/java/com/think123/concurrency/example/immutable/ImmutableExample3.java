package com.think123.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.think123.concurrency.annoations.ThreadSafe;

@ThreadSafe
public class ImmutableExample3 {
    private  static final ImmutableList list= ImmutableList.of(1,2,3);

    private static final ImmutableSet set=ImmutableSet.copyOf(list);

    private static final ImmutableMap<Integer,Integer> map=
            ImmutableMap.of(1,2,3,4,5,6);

    private static final ImmutableMap<Integer,Integer> map1=
            ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).build();

    public static void main(String[] args) {
        list.add(1);

        set.add(5);

        map.put(8,8);
    }
}