package com.bruce.study.algorithm;
/*
 *@ClassName RemoveDuplicatesJava
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/18 10:20
 *@Version 1.0
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *  删除有序数组中的重复元素，返回最终数组大小？(Java 版本)
 *    给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *    不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 */
public class RemoveDuplicatesJava {
    public int removeDuplicates(int[] arr) {
        TreeSet<Integer> set=new TreeSet<Integer>();
        for (int i : arr) {
            set.add(i);
        }
        Iterator<Integer> iterator = set.iterator();
        ArrayList<Integer> list=new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        for (int i=0;i<list.size();i++) {
            arr[i]=list.get(i);
        }
        return set.size();
    }

}