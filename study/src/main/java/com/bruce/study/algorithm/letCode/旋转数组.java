package com.bruce.study.algorithm.letCode;
/*
 *@ClassName 旋转数组
 *@Description 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *@Author Bruce
 *@Date 2020/6/20 0:38
 *@Version 1.0
 */

import java.util.Arrays;

public class 旋转数组 {

    public static void rotate(int[] nums, int k) {

        int size = nums.length;
        if (k >= size) {
            k = k % size;
        }
        int temp = 0;
        for (int i = 0; i < k; i++) {
            temp = nums[nums.length - 1];
            System.out.println("temp =" + temp);
            for (int j = size - 2; j >= 0; j--) {
                nums[j + 1] = nums[j];
                if (j == 0) {
                    nums[j] = temp;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        rotate(arr, 1);
    }
}