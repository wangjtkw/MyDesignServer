package com.example.mydegign.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;


public class ChatRespHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageProto.Message message = (MessageProto.Message) msg;
        if (message == null) {
            ReferenceCountUtil.release(msg);
        } else {
            if (Constant.USER2EMPLOYER_MESSAGE.equals(message.getMsgType())) {
                int clientId = message.getClientID();
                int receiveId = message.getReceiveId();
                EmployerManager employerManager = EmployerManager.getInstance();
                if (employerManager.isOnLine(receiveId)) {
                    Channel receiveChannel = employerManager.getEmployerChannel(receiveId);
                    receiveChannel.writeAndFlush(MessageFactory.getMessage(
                            Constant.USER2EMPLOYER_MESSAGE, clientId, receiveId, message.getBody()
                    ));
                    System.out.println("p1:" + clientId + " to " + "p2:" + receiveId + " msg:" + message.getBody());
                } else {
                    System.out.println("目标用户不在线！");
                }
            } else if (Constant.EMPLOYER2USER_MESSAGE.equals(message.getMsgType())) {
                int clientId = message.getClientID();
                int receiveId = message.getReceiveId();
                UserManager userManager = UserManager.getInstance();
                if (userManager.isOnLine(receiveId)) {
                    Channel receiveChannel = userManager.getUserChannel(receiveId);
                    receiveChannel.writeAndFlush(MessageFactory.getMessage(
                            Constant.EMPLOYER2USER_MESSAGE, clientId, receiveId, message.getBody()
                    ));
                    System.out.println("p1:" + clientId + " to " + "p2:" + receiveId + " msg:" + message.getBody());
                } else {
                    System.out.println("目标用户不在线！");
                }
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
