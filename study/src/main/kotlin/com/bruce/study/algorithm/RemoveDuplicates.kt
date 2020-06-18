package com.bruce.study.algorithm

/*
 *@ClassName RemoveDuplicates
 *@Description 删除有序数组中的重复元素，返回最终数组大小？
 *  给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *  不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *@Author Bruce
 *@Date 2020/6/18 1:14
 *@Version 1.0
 */
class RemoveDuplicates {
    fun removeDuplicates(arr: IntArray): Int {
        val size = arr.size
        var i = 0
        var j = 1
        (j until size).map{
            if (arr[i] == arr[it]){
                j++
            }else{
                i++
                arr[i] = arr[it]
                j++
            }
        }
        println()
        return i+1
    }
}

fun main() {
    val arr = intArrayOf(0, 0,1, 1, 1, 2,3, 9,9 )
    val removeDuplicates = RemoveDuplicates().removeDuplicates(arr)
    println("-------------------------: $removeDuplicates")

}