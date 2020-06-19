package com.bruce.study.algorithm.letCode

/*
 *@ClassName 股票买卖最佳时机
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/19 22:40
 *@Version 1.0
 */
class 股票买卖最佳时机 {

    fun maxProfit(prices: IntArray): Int {
        var ss = 0 //最高利润
//        var index = 0
//        for (i in 1 until prices.size) {
//            // 如果前一个比后一个大
//            if ((prices[i - 1] > prices[i])) {
//                ss += prices[i - 1] - prices[index]
//                index = i
//            } else {
//                // 如果前一个比后一个小
//                if (i == prices.size - 1) {
//                    // 如果当前已经是最后一个数据，就需要获取一下结果了，比如所有的数据都是递增的情况
//                    ss += prices[i] - prices[index]
//                }
//            }
//        }
        for (i in 1 until prices.size) {
            if (prices[i] > prices[i - 1]) {
                ss += prices[i] - prices[i - 1]
            }
        }

        return ss
    }

}

fun main() {
    val ss = intArrayOf(7, 1, 5, 3, 6, 4)
//    val ss = intArrayOf(5,4,3,2,1)
    val time = System.nanoTime()
    val rise = 股票买卖最佳时机().maxProfit(ss)


    println("最大的利润为：$rise  耗时：${System.nanoTime() - time} ms")
}