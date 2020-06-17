package com.bruce.study.algorithm.lru;
/*
 *@ClassName LRUCache
 *@Description 实现一个简单的LRU算法
 *@Author Bruce
 *@Date 2020/6/11 5:22
 *@Version 1.0
 */

import java.util.HashMap;
import java.util.Map;

/**
 * LRU全称是Least Recently Used，即最近最久未使用的意思。
 *
 * LRU算法的设计原则是：如果一个数据在最近一段时间没有被访问到，那么在将来它被访问的可能性也很小。也就是说，当限定的空间已存满数据时，应当把最久没有被访问到的数据淘汰。
 * 实现LRU 三方案对比:
 *      
 * 1.用一个数组来存储数据，给每一个数据项标记一个访问时间戳，每次插入新数据项的时候，先把数组中存在的数据项的时间戳自增，并将新数据项的时间戳置为0并插入到数组中。
 * 每次访问数组中的数据项的时候，将被访问的数据项的时间戳置为0。当数组空间已满时，将时间戳最大的数据项淘汰。
 *
 * 2.利用一个链表来实现，每次新插入数据的时候将新数据插到链表的头部；每次缓存命中（即数据被访问），则将数据移到链表头部；那么当链表满的时候，就将链表尾部的数据丢弃。
 *
 * 3.利用链表和hashmap。当需要插入新的数据项的时候，如果新数据项在链表中存在（一般称为命中），则把该节点移到链表头部，如果不存在，则新建一个节点，放到链表头部，
 * 若缓存满了，则把链表最后一个节点删除即可。在访问数据的时候，如果数据项在链表中存在，则把该节点移到链表头部，否则返回-1。
 * 这样一来在链表尾部的节点就是最近最久未访问的数据项。
 *
 * 对于第一种方法，需要不停地维护数据项的访问时间戳，另外，在插入数据、删除数据以及访问数据时，时间复杂度都是O(n)。
 * 对于第二种方法，链表在定位数据的时候时间复杂度为O(n)。
 * 所以在一般使用第三种方式来是实现LRU算法。
 * 实现方案
 *
 * 使用LinkedHashMap实现
 *      LinkedHashMap底层就是用的HashMap加双链表实现的，而且本身已经实现了按照访问顺序的存储。
 *      此外，LinkedHashMap中本身就实现了一个方法removeEldestEntry用于判断是否需要移除最不常读取的数，方法默认是直接返回false，不会移除元素，所以需要重写该方法。
 *      即当缓存满后就移除最不常用的数。
 * ————————————————
 * 版权声明：本文为CSDN博主「圣小童」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/elricboa/java/article/details/78847305
 * @param <k>
 * @param <v>
 */
public class LRUCache<k, v> {
    //容量
    private int capacity;
    //当前有多少节点的统计
    private int count;
    //缓存节点
    private Map<k, Node<k, v>> nodeMap;
    private Node<k, v> head;
    private Node<k, v> tail;

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.valueOf(capacity));
        }
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        //初始化头节点和尾节点，利用哨兵模式减少判断头结点和尾节点为空的代码
        Node headNode = new Node(null, null);
        Node tailNode = new Node(null, null);
        headNode.next = tailNode;
        tailNode.pre = headNode;
        this.head = headNode;
        this.tail = tailNode;
    }

    public void put(k key, v value) {
        Node<k, v> node = nodeMap.get(key);
        if (node == null) {
            if (count >= capacity) {
                //先移除一个节点
                removeNode();
            }
            node = new Node<>(key, value);
            //添加节点
            addNode(node);
        } else {
            //移动节点到头节点
            moveNodeToHead(node);
        }
    }

    public Node<k, v> get(k key) {
        Node<k, v> node = nodeMap.get(key);
        if (node != null) {
            moveNodeToHead(node);
        }
        return node;
    }

    private void removeNode() {
        Node node = tail.pre;
        //从链表里面移除
        removeFromList(node);
        nodeMap.remove(node.key);
        count--;
    }

    private void removeFromList(Node<k, v> node) {
        // 取出当前要移除节点的前后两个节点对象
        Node pre = node.pre;
        Node next = node.next;

        // 前节点对象的下一个  原本是要删除的节点  变成后节点
        pre.next = next;
        // 后阶段对象的前一个  原本是要删除的节点  变成前节点
        next.pre = pre;

        node.next = null;
        node.pre = null;
    }

    private void addNode(Node<k, v> node) {
        //添加节点到头部
        addToHead(node);
        nodeMap.put(node.key, node);
        count++;
    }

    private void addToHead(Node<k, v> node) {
        Node next = head.next;
        next.pre = node;
        node.next = next;
        node.pre = head;
        head.next = node;
    }

    public void moveNodeToHead(Node<k, v> node) {
        //从链表里面移除
        removeFromList(node);
        //添加节点到头部
        addToHead(node);
    }

    /**
     *  节点对象 双向链表结构 (成员内部类 Node)
     * @param <k>
     * @param <v>
     */
    class Node<k, v> {
        k key;      // 当前指针 key
        v value;    // 当前指针 value
        Node pre;  // 前后的指针
        Node next; // 向后的指针

        public Node(k key, v value) {
            this.key = key;
            this.value = value;
        }
    }
}