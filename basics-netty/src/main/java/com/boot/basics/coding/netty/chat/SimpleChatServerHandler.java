package com.boot.basics.coding.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author cherrishccl
 * @Date 2020/8/11 10:10
 * @Version 1.0
 * @Description
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel inChannel = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[Client] - " + inChannel.remoteAddress() + " 加入\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Channel inChannel = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[Client] - " + inChannel.remoteAddress() + " 离开\n");
        }
        channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel inChannel = ctx.channel();
        for(Channel channel : channels){
            if (channel != inChannel){
                channel.writeAndFlush("[" + inChannel.remoteAddress() + "]" + msg + "\n");
            } else {
                channel.writeAndFlush("[you]" + msg + "\n");
            }
        }
        System.out.println("Client: " + inChannel.remoteAddress() + "在线");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        Channel inChannel = ctx.channel();
        System.out.println("Client: " + inChannel.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        Channel inChannel = ctx.channel();
        System.out.println("Client: " + inChannel.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel inChannel = ctx.channel();
        System.out.println("Client: " + inChannel.remoteAddress() + "异常");
        cause.printStackTrace();
        ctx.close();
    }

}
