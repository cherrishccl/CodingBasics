package com.boot.basics.coding.netty.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author cherrishccl
 * @Date 2020/8/11 10:49
 * @Version 1.0
 * @Description
 */
public class SimpleChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println(s);
    }
}
