package com.bruce.study.algorithm.sort


/*
 *@ClassName QuickSort
 * @ 参考 https://sort.hust.cc/6.quicksort
 *@Description 快速排序
 * 算法步骤
 * 1.从数列中挑出一个元素，称为 “基准”（pivot）;
 * 2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 *@Author Bruce
 *@Date 2020/6/17 14:27
 *@Version 1.0
 */
class QuickSort {

    fun sort(arr: IntArray): IntArray {

        return quickSort(arr,0,arr.size-1)
    }

    fun quickSort(arr: IntArray, left: Int, right: Int): IntArray {
        println("============================")

        if (left < right) {
            val partitionIndex = partition(arr, left, right)
            quickSort(arr, left, partitionIndex - 1)
            quickSort(arr, partitionIndex + 1, right)
        }
        return arr
    }

    /**
     * 设定基准值
     */
    fun partition(arr: IntArray, left: Int, right: Int): Int {
        val pivot = left
        var index = pivot + 1
        for (i in index..right) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index)
                index++
            }
        }
        if(pivot!= index-1){
            swap(arr, pivot, index - 1);
        }
        return index - 1;
    }

    /**
     * 交换两个下标的位置
     */
    fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }

}

fun main() {
    val arr = intArrayOf(0,1, 9, 2, 6, 3,8, 4, 5, 7)
    val sort = QuickSort().sort(arr)
    println("-------------------------")
    sort.forEach { println(it) }
}