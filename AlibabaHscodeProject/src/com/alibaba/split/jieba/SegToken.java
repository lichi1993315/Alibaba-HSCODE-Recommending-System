package com.alibaba.split.jieba;

public class SegToken {
    public String word;


    public SegToken(String word) {
        this.word = word;
    }


    @Override
    public String toString() {
        return word;
    }

}
