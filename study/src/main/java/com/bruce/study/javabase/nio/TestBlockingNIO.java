package com.bruce.study.javabase.nio;
/*
 *@ClassName TestBlockingNIO
 *@Description
 * 一、使用NIO 完成网络通信的三个核心
 *  1. 通道 （Channel）: 负责连接
 *  2. 缓冲区(Buffer)：负责数据存取
 *  3. 选择器（Selector）：是SelectableChannel 的多路复用。用于监控SelectableChannel 的IO状况
 *
 *@Author Bruce
 *@Date 2020/6/26 2:04
 *@Version 1.0
 */


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingNIO {

    // 客户端
    @Test
    public void client() throws IOException {
        // 1. 获取连接
        SocketChannel sChannel1 = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        // 2. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 3.读取本地文件，并发送到服务端
        while (inChannel.read(buf)!=-1){
            buf.flip();
            sChannel1.write(buf);
            buf.clear();
        }

        // 4.关闭通道
        inChannel.close();
        sChannel1.close();
    }

    // 服务端
    @Test
    public void server() throws IOException{
        // 1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);

        // 2.绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        // 3.获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();

        // 4.分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 5.接受客户端的数据，并保存到本地
        while (sChannel.read(buf)!=-1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        // 6.关闭通道
        ssChannel.close();
        outChannel.close();
        sChannel.close();

    }
}