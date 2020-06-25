package com.bruce.study.algorithm.letCode;
/*
 *@ClassName Fib
 *@Description 斐波那契数列
 *@Author Bruce
 *@Date 2020/6/24 1:40
 *@Version 1.0
 */

import java.util.HashMap;

public class Fib {
    public static void main(String[] args) {
        fib(10);
    }

    public static HashMap<Integer, Integer> cache = new HashMap<>();

    public static int fib(int N) {
        if (cache.containsKey(N)) {
            System.out.println("存在key:" + N + "-----------------" + cache.get(N));
            return cache.get(N);
        }
        int result = 0;
        if (N <= 1) {
            return N;
        } else {
            result = fib(N - 1) + fib(N - 2);
        }
        System.out.println(N + "================ " + result);
        cache.put(N, result);

        return result;
    }
}