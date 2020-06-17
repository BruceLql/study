package com.bruce.study.algorithm.sort;
/*
 *@ClassName InsertionSertJava
 * @参考 https://sort.hust.cc/3.insertionsort
 *@Description 插入排序：它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 1.将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 * 2.从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 *@Author Bruce
 *@Date 2020/6/17 20:49
 *@Version 1.0
 */

public class InsertionSertJava {
    public static int[] insertionSert(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            // 取出一个值
            int current = arr[i + 1];
            // 当前确定已排序好的中间节点下标
            int preIndex = i;

            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex+1] = arr[preIndex];
                preIndex--;
            }
            if(preIndex != i){
                arr[preIndex+1] = current;
            }
        }

        return arr;
    }
    public static void main(String[] args) {
        int[] arr =  {2,1,3,7,6,5,9,0,4,8};
        int[] ints = insertionSert(arr);
        for (int ss:ints) {
            System.out.println(ss);
        }
    }

}