package com.bruce.study.javabase;
/*
 *@ClassName Locktest
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/21 10:05
 *@Version 1.0
 */

import java.util.concurrent.locks.ReentrantLock;

public class Locktest {
    static A a;

    public static void test(){
        synchronized (a){
            System.out.println("-------------------syncing-------------");
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        System.out.println("hello");
        lock.unlock();
    }
}

class A{

}