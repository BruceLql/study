package com.bruce.study.algorithm.sort;
/*
 *@ClassName SelectionSortJava
 *@Description 选择排序:选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。所以用到它的时候，数据规模越小越好。
 * 唯一的好处可能就是不占用额外的内存空间了吧。
 *
 * 算法步骤
 * 1.首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
 * 2.再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 3.重复第二步，直到所有元素均排序完毕。
 *@Author Bruce
 *@Date 2020/6/17 20:03
 *@Version 1.0
 */

public class SelectionSortJava {

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 7, 6, 5, 9, 0, 4, 8};
        int[] ints = selectionSort(arr);
//         int[] ints = SelectionSort.Companion.selectionSort(arr);
        for (int ss : ints) {
            System.out.println(ss);
        }

    }
}