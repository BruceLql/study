package com.bruce.study.algorithm.letCode;
/*
 *@ClassName 动态规划上台阶
 *@Description
 *@Author Bruce
 *@Date 2020/6/20 3:21
 *@Version 1.0
 */

import java.util.Arrays;

public class 动态规划上台阶 {
    /**
     * 简单的一维 DP 问题描述：一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *
     * @param n 台阶数
     * @return int 方案数
     */
    public static int test(int n) {
        if (n <= 1) return n;
//        (1)、定义数组元素的含义
        int[] dp = new int[n + 1];
//       （2）、找出数组元素间的关系式
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
//        通过关系式来计算出 dp[n]
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
//         把最终结果返回
        return dp[n];
    }

    public static void main(String[] args) {
        int[][] ss = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};


//        int test = test(15);
        int test = uniquePaths(ss);
        System.out.printf("共有 %d 种方案", test);
    }


    /**
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] dp = new int[m][n]; // 步骤一、定义数组元素的含义
//        初始化：找出关系数组元素间的关系式
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
//       找出关系数组元素间的关系式,推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * <p>
     * 说明：每次只能向下或者向右移动一步。
     *
     * @return
     */
    public static int uniquePaths(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;

        if (m <= 0 || n <= 0) return 0;
//        步骤一、定义数组元素的含义
        int[][] dp = new int[m][n];
//        步骤二：找出关系数组元素间的关系式
        // 初始化
        dp[0][0] = arr[0][0];
        // 初始化最上边的列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        // 初始化最左边的列
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }

        // 推导出 dp[m-1][n-1]

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


}