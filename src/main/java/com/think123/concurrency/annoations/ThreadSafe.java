package com.think123.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *    线程[安全]的类或者写法的标识
 */
@Target(ElementType.TYPE)//注解的目标位置
@Retention(RetentionPolicy.SOURCE)//注解存在的时间
public @interface ThreadSafe {
}
