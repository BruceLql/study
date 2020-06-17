package com.bruce.study.mianshi;
/*
 *@ClassName Mianshi
 *@Description 面试题
 *@Author Bruce
 *@Date 2020/6/14 21:09
 *@Version 1.0
 * 1.用递归写二分查找
 * 2.静态和实例变量的区别
 *  静态变量 由static 修饰，会在类加载的时候完成初始化，可以直接 类名.静态变量 去引用
 *  实例变量  引用  必须先实例化类对象，才能引用这个实例变量
 *
 * 3.final用处:
 * 可以用来修饰  类、方法、变量
 *
 * final 修饰的类 不能被继承
 *       修饰的方法 不能被覆写
 *       修饰的常量在编译阶段会放入到常量池中
 *       修饰的变量 不可变
 *
 *
 *
 * 4.spring mvc原理
 *
 *
 * 5.spring事物的特性、传播行为和隔离级别
 * 4个特性：原子性、一致性、隔离性、永久性
 * 4个隔离界别：read uncommited、 read commited、repeatable Read、 serializable
 * 7个传播特性： required、supports、mandatory、not_supports、nested、never、required_new、
 *
 *
 * 大题：JAVA类型的算法题。或者三个sql语句题
    编写一个函数，输入行数，打印一个金字塔如下
       a
      aaa
     aaaaa
    aaaaaaa
 */

public class Mianshi {


    public static void main(String[] args) {
        int[] arr = {4, 5, 9, 7, 6, 1, 3, 8, 2, 0};

        arr = bubbleSort(arr);

        for (int i = 0; i < arr.length; i++) {
            int ss = arr[i];
            System.out.print(ss);
        }
        int i = test333(arr, 0, arr.length - 1, 5);
        System.out.println("==================================: 5 的下标：" + i);

    }

    /**
     * 冒泡排序: 比较相邻的两个元素，把大的往右移
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {

        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }


    /**
     * 选择排序：
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * <p>
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * <p>
     * 重复第二步，直到所有元素均排序完毕。
     *
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {

        int len = arr.length;
        if (len == 0) return arr;

        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i; j < len; j++) {

                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

        }
        return arr;
    }

    /**
     * 插入排序：
     * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
     * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
     *
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr) {

        int len = arr.length;
        if (len == 0) return arr;


        for (int i = 0; i < len - 1; i++) {
            int current = arr[i + 1]; // 取下一个值（从下标 1 开始）
            int preIndex = i; // 有序节点边界下标
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            if (preIndex != i) {
                arr[preIndex + 1] = current;
            }

        }
        return arr;
    }


    /*
     * 二分查找，返回索引
     */
    private static int find(int[] arr, int searchKey) {
        int lowerBound = 0;
        int upperBound = arr.length - 1;
        int curIn;
        while (lowerBound <= upperBound) {
            curIn = (lowerBound + upperBound) / 2;
            if (arr[curIn] == searchKey) {
                return curIn;
            } else {
                if (arr[curIn] < searchKey) {
                    lowerBound = curIn + 1;
                } else {
                    upperBound = curIn - 1;
                }
            }
        }
        return -1;
    }

    /*
     * 二分查找，返回索引，递归实现
     */
    private static int recursiveFind(int[] arr, int start, int end, int searchKey) {
        System.out.println("=========== 递归调用 ：recursiveFind() ==============");
        if (start <= end) {
            int middle = (start + end) / 2;
            if (searchKey == arr[middle]) {
                return middle;
            } else if (searchKey < arr[middle]) {
                return recursiveFind(arr, start, middle - 1, searchKey);
            } else {
                return recursiveFind(arr, middle + 1, end, searchKey);
            }
        } else {
            return -1;
        }
    }

    /**
     * 递归实现二分查找
     *
     * @param arr
     * @return
     */
    public static int test(int[] arr, int start, int end, int searchKey) {

        if (start <= end) {
            int mid = (start + end) / 2;
            if (searchKey == arr[mid]) {
                return mid;
            } else if (arr[mid] < searchKey) {
                return test(arr, mid + 1, end, searchKey);
            } else {
                return test(arr, 0, mid - 1, searchKey);
            }
        } else {
            return -1;
        }
    }

//    public static void main(String[] args) {
//        print(4);
//    }

    /**
     * 打印三角形
     *
     * @param num
     */
    public static void print(int num) {

        for (int i = 0; i < num; i++) {
            for (int j = 1; j < 2 * num; j++) {

                if (j <= num && (num - j) <= i) {
                    System.out.print("*");
                } else if (j > num && (j - num) <= i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();

        }
    }

    public void printStar(int start, int count) {
        for (int i = 0; i < start; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static int test333(int[] arr,int start,int end,int searchKey) {

        if(start <= end){
            int mid =(start+end)/2;
            if(arr[mid] == searchKey){
                return mid;
            }else if(arr[mid] > searchKey) {
                return test333(arr,start,mid-1,searchKey);
            }else if(arr[mid] < searchKey) {
                return test333(arr,mid+1,end,searchKey);
            }
        }else {
            return -1;
        }
        return -1;
    }



}