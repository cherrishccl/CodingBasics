package com.boot.basics.coding.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author cherrishccl
 * @Date 2020/8/11 9:44
 * @Version 1.0
 * @Description
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private final ByteBuf firstMessage;

    public EchoClientHandler() {
        this.firstMessage = Unpooled.buffer(EchoClient.SIZE);
        for(int i = 0; i < firstMessage.capacity(); i++){
            firstMessage.writeByte((byte)i);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
