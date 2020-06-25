package com.bruce.study.javabase.nio;
/*
 *@ClassName TestNonBlockingNIO2
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/26 5:15
 *@Version 1.0
 */

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.WeakHashMap;

public class TestNonBlockingNIO2 {

    @Test
    public void send()throws IOException {
        DatagramChannel dc = DatagramChannel.open();

        dc.configureBlocking(false);

        ByteBuffer buff = ByteBuffer.allocate(1024);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()){
            String str = sc.next();
            buff.put((new Date().toString()+"\n"+str).getBytes());
            buff.flip();
            dc.send(buff,new InetSocketAddress("127.0.0.1",9898));
            buff.clear();
        }
        dc.close();


    }

    @Test
    public void receive()throws IOException {
        DatagramChannel dc = DatagramChannel.open();

        dc.configureBlocking(false);

        dc.bind(new InetSocketAddress(9898));

        Selector selector = Selector.open();

        dc.register(selector, SelectionKey.OP_READ);

        while (selector.select() >0){
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()){
                SelectionKey sk = it.next();

                if (sk.isReadable()){
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    dc.receive(buf);

                    buf.flip();

                    System.out.println(new String(buf.array(),0,buf.limit()));

                    buf.clear();
                }
                it.remove();

            }
        }
    }
}