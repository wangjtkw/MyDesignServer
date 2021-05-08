package com.example.mydegign.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class EmployerLoginAuthRespHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.writeAndFlush(MessageFactory.getMessage(Constant.SUCCESS, "hello"));
        System.out.println("run asd");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageProto.Message message = (MessageProto.Message) msg;
        if (message == null) {
            ReferenceCountUtil.release(msg);
        } else {
            if (Constant.EMPLOYER_LOGIN.equals(message.getMsgType())) {
                System.out.println("run2");
                int clientId = message.getClientID();
                System.out.println("clientId:" + clientId);
                EmployerManager.getInstance().addEmployer(clientId, ctx.channel());
                ctx.channel().writeAndFlush(MessageFactory.getMessage(Constant.EMPLOYER_LOGIN, Constant.SUCCESS));
            } else {
                super.channelRead(ctx, msg);
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel() != null) {
            UserManager.getInstance().removeChannel(ctx.channel());
        } else {
            super.channelInactive(ctx);
        }
    }

}

