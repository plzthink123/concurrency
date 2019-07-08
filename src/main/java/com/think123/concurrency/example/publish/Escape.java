package com.think123.concurrency.example.publish;

import com.think123.concurrency.annoations.NotRecommend;
import com.think123.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象溢出
 * 在对象未构建完成时,不能为外部访问
 */
@NotRecommend
@NotThreadSafe
@Slf4j

public class Escape {

    private int thisCanBeEscape=0;

    public Escape(){
        new Innerclass();
    }
    private class Innerclass{

        public Innerclass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}