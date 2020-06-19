package com.bruce.study.algorithm.letCode

import java.util.*

/*
 *@ClassName StringArray01
 *@Description 找出所有 长度>=5的字符串，并且忽略大小写、去除重复字符串，然后按字母排序，最后用“爱心❤”连接成一个字符串输出！
 *@Author Bruce
 *@Date 2020/6/18 17:16
 *@Version 1.0
 */
class `26` {
    fun test(arr: ArrayList<String>): String {
        val set = TreeSet<String>()
        var str = StringBuffer()
        arr.forEach {
            println(it)
            if (it.toString().length >=5) {set.add(it.toString().toLowerCase())}
        }
        println("-------------------------------")
        set.forEach{ println(it)}
        println("-------------------------------")
        val sortSet = set
        sortSet.forEach{
            println(it)
        }

        set.forEach{
            str.append(it.toString()).append("爱心❤")
        }
        str = str.delete(str.length-3,str.length)
        return str.toString()
    }


}

fun main() {
    val arr = arrayListOf<String>("1", "CHEERS","2",  "of", "codesheep","Aaaaasssd", "bilibili","5", "at", "BILIBILI", "codesheep", "23",  "6")
    val test = `26`().test(arr)
    println(test)


}