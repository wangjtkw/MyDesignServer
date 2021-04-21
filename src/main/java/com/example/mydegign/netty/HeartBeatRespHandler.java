package com.example.mydegign.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        UserManager.getInstance().removeChannel(ctx.channel());
        EmployerManager.getInstance().removeChannel(ctx.channel());
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageProto.Message message = (MessageProto.Message) msg;
        if (message == null) {
            ReferenceCountUtil.release(msg);
        } else if (Constant.PING.equals(message.getMsgType())) {
            if (Constant.PING_STR.equals(message.getBody())) {
                ctx.writeAndFlush(MessageFactory.getMessage(Constant.PING, Constant.OK_STR));
                System.out.println("完成心跳");
            } else {
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //退出登录
        System.out.println("服务端已关闭");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("客户端读超时");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("客户端写超时");
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("客户端所有操作超时");
            }
        }

    }


}
