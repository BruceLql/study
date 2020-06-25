package com.bruce.study.javabase.nio;
/*
 *@ClassName TestBuffer
 *@Description
 * 一、缓冲区（Buffer）:在Java NIO 中负责数据的存取。 缓冲区就是数组。用于存取不同的数据类型的数据
 * 根据不同的数据类型(boolean 除外)，提供了相应的缓冲区：
 *     ByteBuffer
 *     CharBuffer
 *     ShortBuffer
 *     IntBuffer
 *     LongBuffer
 *     DoubleBuffer
 *  上述缓冲区的管理方式一致，通过 allocate() 获取缓冲区
 * 二、缓冲区存取数据的两个核心方法：
 *      put();
 *      get();
 * 三、缓冲区的四个核心属性
     capacity: 容量，表示缓冲区中的最大存取数据的容量。一旦声明不能改变。
     limit ：界限，表示缓冲区可以操作的数据大小（limit 后数据不能进行读写）
     position： 位置， 表示缓冲区中正在操作数据的位置。
 *   mark : 标记，表示记录当前position的位置。可以通过reset() 恢复到 mark 的位置
 *
 * 0 <= mark <= position <= limit <= capacity
 *
 *  四、直接缓冲区与非直接缓冲区：
 *     非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在JVM 的内存中
 *      直接缓冲区： 通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 *
 *
 *@Author Bruce
 *@Date 2020/6/26 0:31
 *@Version 1.0
 */

import jdk.nashorn.internal.runtime.AllocationStrategy;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class TestBuffer {

    public void test1(){
        String str = "abcde";

        // 1. 分配一个指定大小的缓冲区
        ByteBuffer buff = ByteBuffer.allocate(1024);
        System.out.println("-------------------------allocate()----------------");
        System.out.println(buff.position());
        System.out.println(buff.limit());
        System.out.println(buff.capacity());
        // 2. 利用 put() 存入数据到缓冲区
        buff.put(str.getBytes());
        System.out.println("-------------------------put()----------------");
        System.out.println(buff.position());
        System.out.println(buff.limit());
        System.out.println(buff.capacity());
        // 3. 利用 flip() 读取数据
        buff.flip();
        System.out.println("-------------------------flip()----------------");
        System.out.println(buff.position());
        System.out.println(buff.limit());
        System.out.println(buff.capacity());

        // 4. 利用 get() 读取缓冲区中的数据
        byte[] dst = new byte[buff.limit()];
        buff.get(dst);
        System.out.println("-------------------------get()----------------");
        System.out.println(new String(dst,0,dst.length));
        System.out.println(buff.position());
        System.out.println(buff.limit());
        System.out.println(buff.capacity());
        // 5. rewind(): 可重复读数据
        buff.rewind();
        System.out.println("-------------------------rewind()----------------");
        System.out.println(buff.position());
        System.out.println(buff.limit());
        System.out.println(buff.capacity());

        // 6. clear() 清空缓冲区. 但是缓冲区的数据依然存在，但是处于“被遗忘”状态
        buff.clear();
        System.out.println("-------------------------clear()----------------");
        System.out.println(buff.position());
        System.out.println(buff.limit());
        System.out.println(buff.capacity());

        System.out.println((char) buff.get());


    }

    public void testAllocateDirect(){
        // 分配直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        System.out.println(byteBuffer.isDirect());

    }

    public static void main(String[] args) {
//       new TestBuffer().test1();
       new TestBuffer().testAllocateDirect();
    }


}