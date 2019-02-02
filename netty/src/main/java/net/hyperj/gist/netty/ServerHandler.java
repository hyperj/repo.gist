package net.hyperj.gist.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.hyperj.gist.common.kit.JavaScriptKit;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf in = (ByteBuf) msg;
        byte[] req = new byte[in.readableBytes()];
        in.readBytes(req);
        String body = new String(req, UTF_8);
        System.out.println("Client Msg: " + body);
        String calResult = null;
        try {
            calResult = JavaScriptKit.Instance.eval(body).toString();
        } catch (Exception e) {
            calResult = "Input Format Error: " + e.getMessage();
        }
        ctx.write(Unpooled.copiedBuffer(calResult.getBytes()));
    }

    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}