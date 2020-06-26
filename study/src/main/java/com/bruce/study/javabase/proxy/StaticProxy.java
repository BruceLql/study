package com.bruce.study.javabase.proxy;
/*
 *@ClassName StaticProxy
 *@Description 静态代理
 * 静态代理 基于接口实现,必须实现同一个接口
 *@Author Bruce
 *@Date 2020/6/26 15:02
 *@Version 1.0
 */

public class StaticProxy {
    public static void main(String[] args) {
        Singer singer = new Agent(new Star());
        singer.sing();
    }
}
/**
 * 代理实现，代理了歌星，唱歌的时候 会先在歌手唱歌之前收钱，然后再唱歌
 */
class Agent implements Singer {
    Star s;
    public Agent(Star s) {
        super();
        this.s = s;
    }
    @Override
    public void sing() {
        System.out.println("在歌手唱歌之前收钱....");
        s.sing();
    }
}
/**
 * 真实实现，一个歌星
 */
class Star implements Singer {
    @Override
    public void sing() {
        System.out.println("Star Singing~~~");
    }
}
/**
 * 最顶层接口 歌手
 */
interface Singer {
    void sing();
}