package com.bruce.study.singleton;
/*
 *@ClassName SingletonDoubleCheck
 *@Description 双重检查锁的方式实现 单例
 *@Author Bruce
 *@Date 2020/6/12 7:47
 *@Version 1.0
 */

public class SingletonDoubleCheck {
    private volatile SingletonDoubleCheck singletonDoubleCheck;

    public SingletonDoubleCheck() {
    }

    public SingletonDoubleCheck getSingletonDoubleCheck(){

        if(singletonDoubleCheck == null){
            synchronized (SingletonDoubleCheck.class){
                if(singletonDoubleCheck == null ){
                    singletonDoubleCheck = new SingletonDoubleCheck();
                }
            }
        }
        return singletonDoubleCheck;
    }


    public SingletonDoubleCheck getInstance(){

        if(singletonDoubleCheck == null){
            synchronized (SingletonDoubleCheck.class){
                if(singletonDoubleCheck == null) singletonDoubleCheck = new SingletonDoubleCheck( );
            }
        }
        return singletonDoubleCheck;
    }
}