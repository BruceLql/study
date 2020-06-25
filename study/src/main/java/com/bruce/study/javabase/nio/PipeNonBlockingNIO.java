package com.bruce.study.javabase.nio;
/*
 *@ClassName PipeNonBlockingNIO
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/26 5:39
 *@Version 1.0
 */

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeNonBlockingNIO {

    @Test
    public void test1() throws IOException {
        // 1.获取管道
        Pipe pipe = Pipe.open();

        // 2.将缓冲区中的数据写入管道(单独方一个线程)
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sinkChannel = pipe.sink();
        buf.put("通过向项管道发送数据".getBytes());
        buf.flip();
        sinkChannel.write(buf);

        // 3.读取缓冲区中的数据(单独放一个线程)
        Pipe.SourceChannel sourceChannel = pipe.source();
        buf.flip();
        int len = sourceChannel.read(buf);
        System.out.println(new String(buf.array(),0,len));

        sourceChannel.close();
        sinkChannel.close();

    }
    @Test
    public void test2()throws IOException{

    }
}