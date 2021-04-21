//package com.example.mydegign.NettyClient;
//
//import com.example.mydegign.netty.Constant;
//import com.example.mydegign.netty.MessageFactory;
//import com.example.mydegign.netty.MessageProto;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
//public class ChatReqHandler extends ChannelInboundHandlerAdapter {
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        MessageProto.Message message = (MessageProto.Message) msg;
//        if (message == null) {
//            super.channelRead(ctx, msg);
//        } else {
//            if (Constant.MESSAGE.equals(message.getMsgType())) {
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        int clientId = message.getClientID();
//                        int receiveId = message.getReceiveId();
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        ctx.writeAndFlush(MessageFactory.getMessage(
//                                Constant.MESSAGE, receiveId, clientId, System.currentTimeMillis() + ":msg"));
//                        System.out.println("收到的消息：" + message.getBody());
//
//                    }
//                }).start();
//
//            }
//        }
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//        ctx.writeAndFlush(MessageFactory.getMessage(Constant.MESSAGE, 1, 2, "hello"));
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//}
