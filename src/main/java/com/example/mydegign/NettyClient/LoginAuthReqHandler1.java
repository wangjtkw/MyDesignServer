//package com.example.mydegign.NettyClient;
//
//import com.example.mydegign.netty.Constant;
//import com.example.mydegign.netty.MessageFactory;
//import com.example.mydegign.netty.MessageProto;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
//public class LoginAuthReqHandler1 extends ChannelInboundHandlerAdapter {
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        MessageProto.Message message = (MessageProto.Message) msg;
//        System.out.println("run1");
//        if (message == null) {
//            System.out.println("null");
//            super.channelRead(ctx, msg);
//        } else {
//            System.out.println("message" + message.getMsgType());
//            System.out.println("message" + message.getBody());
//            if (Constant.LOGIN.equals(message.getMsgType())) {
//                if (message.getBody().equals(Constant.SUCCESS)) {
//                    System.out.println("登录成功！");
//                    ctx.pipeline().remove(this);
//                    ctx.fireChannelActive();
//                } else {
//                    System.out.println("登录失败");
//                    ctx.channel().close();
//                }
//            }
//        }
//
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channelActive run log1");
//        MessageProto.Message message = MessageFactory.getMessage(Constant.LOGIN, 1, "logging");
//        ctx.writeAndFlush(message);
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//
//}
