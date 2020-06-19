package com.bruce.study.algorithm.letCode;
/*
 *@ClassName StringArray01Java
 *@Description 找出所有 长度>=5的字符串，并且忽略大小写、去除重复字符串，然后按字母排序，最后用“爱心❤”连接成一个字符串输出！
 *@Author Bruce
 *@Date 2020/6/18 20:27
 *@Version 1.0
 */

import kotlin.jvm.JvmStatic;

import java.util.TreeSet;

public class StringArray02Java {

    public static String test(String[] arr) {
        TreeSet<String> set = new TreeSet<>();
        StringBuffer str = new StringBuffer();
        for (String i : arr) {
            if (i.length() > 5) {
                System.out.println(i);
                set.add(i.toLowerCase());
            }
        }
        System.out.println("-------------------");
        set.forEach(x -> System.out.println(x));
        set.forEach(x -> {
            str.append(x).append("爱心❤");
        });

        str.delete(str.length()-3,str.length());

        return str.toString();
    }


    public static void main(String[] args) {
        String[] arr = {"1", "CHEERS", "2", "of", "codesheep", "Aaaaasssd", "bilibili", "5", "at", "BILIBILI", "codesheep", "23", "6"};
        String test = test(arr);
        System.out.println("result======================");
        System.out.println(test);
    }


}