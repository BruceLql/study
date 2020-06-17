package com.bruce.study.algorithm.hanoiTower

/*
 *@ClassName hanoi
 *@Description kotlin版本的 汉诺塔算法实现
 *@Author Bruce
 *@Date 2020/6/15 22:19
 *@Version 1.0
 */
class Hanoi {

    fun hanota(A: List<Int>, B: List<Int>, C: List<Int>): Unit {
        val move = move(A.size, A as ArrayList<Int>, B as ArrayList<Int>, C as ArrayList<Int>)
        println(move)
    }

    fun move(size: Int, A: ArrayList<Int>, B: ArrayList<Int>, C: ArrayList<Int>) :ArrayList<Int>{
        if (size == 1) {
            C.add(A.removeAt(A.size - 1))
        } else {
            move(size - 1, A, C, B)
            C.add(A.removeAt(A.size - 1))
            move(size - 1, B, A, C)
        }
        return C

    }
}

fun main( args: Array<String>) {

    val A = arrayListOf<Int>(5,4,3,2,1)
    val B = ArrayList<Int>()
    val C = ArrayList<Int>()
    Hanoi().hanota(A,B,C)


}