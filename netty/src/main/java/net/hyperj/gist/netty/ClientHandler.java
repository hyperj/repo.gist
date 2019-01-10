package net.hyperj.gist.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

import static net.hyperj.gist.common.dict.StringDict.CHARSET_UTF8;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    ChannelHandlerContext ctx;

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    public boolean sendMsg(String msg) {
        System.out.println("Client Msg: " + msg);
        byte[] req = msg.getBytes();
        ByteBuf m = Unpooled.buffer(req.length);
        m.writeBytes(req);
        ctx.writeAndFlush(m);
        return msg.equals("q") ? false : true;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, CHARSET_UTF8);
        System.out.println("Server Msg: " + body);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}