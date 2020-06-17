package com.bruce.study

import kotlin.math.min

/*
 *@ClassName Hanoi
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/17 7:29
 *@Version 1.0
 */
class Hanoi {
    /**
     * @param n 塔的层高
     * @param A 迁出桩
     * @param B 辅助桩
     * @param C 迁入桩
     */
    fun hanoi(n: Int, A: Char, B: Char, C: Char) {

        if (n == 1) {
            move(A, C)
        } else {
            hanoi(n - 1, A, C, B)
            move(A, C)
            hanoi(n - 1, B, A, C)
        }

    }

    fun move(A: Char, C: Char) {

        println("$A ---> $C")
    }

    /**
     * 二分查找
     */
    fun coursiveFind(arr: IntArray, start: Int, end: Int, searchKey: Int): Int {

        if (start <= end) {
            var mid: Int = (start + end) / 2
            if (arr[mid] == searchKey) {
                return mid
            } else if (arr[mid] < searchKey) {
                return coursiveFind(arr, mid + 1, end, searchKey)
            } else {
                return coursiveFind(arr, start, mid - 1, searchKey)
            }
        } else {
            return -1
        }

    }

    fun test(intArray: IntArray, searchKey: Int): Int {
        return coursiveFind(intArray, 0, intArray.size - 1, searchKey)
    }

    /**
     * 快速排序
     */
    fun quickSort(){

    }

}

fun main() {
//    Hanoi().hanoi(4,'A','B','C')

    val arr = intArrayOf(0, 1, 2, 3, 4, 5, 6 ,7, 8, 9, 10,500)

    arr.forEach { println(it) }
    val searchKey = 500
    println(arr.size - 1)

    val test = Hanoi().test(arr, searchKey)
    println("$searchKey  的下标是$test")
}