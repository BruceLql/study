package com.bruce.study.algorithm.sort

/*
 *@ClassName InsertionSert
 *@参考 https://sort.hust.cc/3.insertionsort
 *@Description 插入排序：它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 1.将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 * 2.从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 *@Author Bruce
 *@Date 2020/6/17 21:01
 *@Version 1.0
 */
class InsertionSert {

    fun insertionSort(arr: IntArray): IntArray {
        val size = arr.size
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (i in 1..size - 2) {
            val current = arr[i + 1]
            var preIndex = i

            while (preIndex >= 0 && arr[preIndex] > current){
                arr[preIndex+1] =  arr[preIndex]
                preIndex--
            }
            if(preIndex != i){
                arr[preIndex+1] = current
            }
        }
        return arr;
    }

}
fun main() {
    val arr = intArrayOf(0,1, 9, 2, 6, 3,8, 4, 5, 7)
    val bubbleSort = InsertionSert().insertionSort(arr)
    println("-------------------------")
    bubbleSort.forEach { println(it) }
}