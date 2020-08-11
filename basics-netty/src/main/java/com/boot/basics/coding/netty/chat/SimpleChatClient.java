package com.boot.basics.coding.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author cherrishccl
 * @Date 2020/8/11 10:52
 * @Version 1.0
 * @Description
 */
public class SimpleChatClient {
    public static void main(String[] args) {
        new SimpleChatClient("localhost", 8080).start();
    }
    private final String host;
    private final int port;

    public SimpleChatClient(String host, int port){
        this.host = host;
        this.port = port;
    }
    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        try {
            b.group(group).channel(NioSocketChannel.class).handler(new SimpleChatClientInitializer());
            Channel channel = b.connect(host, port).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                channel.writeAndFlush(in.readLine() + "\r\n");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
