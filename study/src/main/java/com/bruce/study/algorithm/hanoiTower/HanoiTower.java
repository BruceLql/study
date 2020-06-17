package com.bruce.study.algorithm.hanoiTower;
/*
 *@ClassName HanoiTower
 *@Description
 * 汉诺塔问题是一个经典的问题。汉诺塔（Hanoi Tower），又称河内塔，源于印度一个古老传说。
 * 大梵天创造世界的时候做了三根金刚石柱子，在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。
 * 大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。
 * 并且规定，任何时候，在小圆盘上都不能放大圆盘，且在三根柱子之间一次只能移动一个圆盘。问应该如何操作？
————————————————
原文链接：https://blog.csdn.net/qq_41705423/java/article/details/82025409
 *@Author Bruce
 *@Date 2020/6/15 16:51
 *@Version 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class HanoiTower {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();
        A.add(2);
        A.add(1);
        A.add(0);

        hanota(A, B, C);
    }

    /**
     * @param A
     * @param B
     * @param C
     */
    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        List<Integer> move = move(A.size(), A, B, C);
        System.out.println(move);
    }

    /**
     *
     * @param size
     * @param A
     * @param B
     * @param C
     * @return
     */
    public static List<Integer> move(int size, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (size == 1) {
            C.add(A.remove(A.size()-1));
        }else {
            move(size-1,A,C,B);
            C.add(A.remove(A.size()-1));
            move(size-1,B,A,C);
        }
        return C;


    }


}