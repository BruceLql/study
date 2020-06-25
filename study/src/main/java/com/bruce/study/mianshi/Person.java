package com.bruce.study.mianshi;
/*
 *@ClassName Persion
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/21 18:02
 *@Version 1.0
 */

public class Person {
    public Person() {
        System.out.println("-------------- Person 构造函数-----");
    }

    private String name;
    private String sex;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String hello(String name){
        System.out.println("hello "+ name);
        return "hello "+name;
    }
}