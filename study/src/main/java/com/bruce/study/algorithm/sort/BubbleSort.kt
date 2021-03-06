package com.bruce.study.algorithm.sort

/*
 *@ClassName BubbleSort
 *@Description 冒泡排序：
 * 冒泡排序（Bubble Sort）也是一种简单直观的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。

 * 作为最简单的排序算法之一，冒泡排序给我的感觉就像 Abandon 在单词书里出现的感觉一样，每次都在第一页第一位，所以最熟悉。
 * 冒泡排序还有一种优化算法，就是立一个 flag，当在一趟序列遍历中元素没有发生交换，则证明该序列已经有序。但这种改进对于提升性能来说并没有什么太大作用
 *@Date 2020/6/17 14:26
 *@Version 1.0
 */
class BubbleSort {


    fun bubbleSort(arr: IntArray): IntArray {
        val n = arr.size
        var out =0
        var inner = 0
        var total = 0

        (1..n - 1).map {
            println("外层循环：${++out}")
            for (j in 0..n - 1 - it) {

                if (arr[j] > arr[j + 1]) {
                    val max =arr[j]
                    arr[j] = arr[j+1]
                    arr[j+1] = max
                }
                println("总次数：${++total} 内层循环：${++inner}")

            }
        }
        return arr
    }
    fun bubbleSort2(arr: IntArray): IntArray {
        val len = arr.size
        for (i in 0 until len - 1) {
            for (j in 0 until len - 1 - i) {
                if (arr[j] > arr[j + 1]) {
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
        return arr
    }

}

fun main() {
    val arr = intArrayOf(0,1, 9, 2, 6, 3,8, 4, 5, 7)
    arr.forEach { println(it) }
    val bubbleSort = BubbleSort().bubbleSort2(arr)
    println("-------------------------")
    bubbleSort.forEach { println(it) }
}