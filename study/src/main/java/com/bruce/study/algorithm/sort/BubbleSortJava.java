package com.bruce.study.algorithm.sort;
/*
 *@ClassName BubbleSort
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/17 20:02
 *@Version 1.0
 */

public class BubbleSortJava {

    public static int[] bubbleSort(int[] arr){
        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr =  {2,1,3,7,6,5,9,0,4,8};
        int[] ints = bubbleSort(arr);
        for (int ss:ints) {
            System.out.println(ss);
        }
    }

}