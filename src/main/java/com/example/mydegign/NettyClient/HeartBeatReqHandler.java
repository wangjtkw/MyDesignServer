//package com.example.mydegign.NettyClient;
//
//import com.example.mydegign.netty.Constant;
//import com.example.mydegign.netty.MessageFactory;
//import com.example.mydegign.netty.MessageProto;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.handler.timeout.IdleState;
//import io.netty.handler.timeout.IdleStateEvent;
//
//public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        MessageProto.Message message = (MessageProto.Message) msg;
//        if (message == null) {
//            super.channelRead(ctx, msg);
//        } else if (Constant.PING.equals(message.getMsgType())) {
//            if (Constant.PONG_STR.equals(message.getBody())) {
//                ctx.writeAndFlush(MessageFactory.getMessage(Constant.PING, Constant.PING_STR));
//            } else if (Constant.OK_STR.equals(message.getBody())) {
//                System.out.println("心跳续约");
//            } else {
//                ctx.fireChannelRead(msg);
//            }
//        } else {
//            ctx.fireChannelRead(msg);
//        }
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        //直接跳转到下个handler请求
//        ctx.fireChannelActive();
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        //退出登录
////        UserChannelUtil.unBindUser(ctx.channel());
////        LoginUtil.logoOut(ctx.channel());
////        System.out.println("客户端已关闭");
//    }
//
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        super.userEventTriggered(ctx, evt);
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent event = (IdleStateEvent) evt;
//            if (event.state() == IdleState.READER_IDLE) {
//                System.out.println("客户端读超时");
//            } else if (event.state() == IdleState.WRITER_IDLE) {
//                System.out.println("客户端写超时");
//            } else if (event.state() == IdleState.ALL_IDLE) {
//                System.out.println("客户端所有操作超时");
//            }
//            ctx.writeAndFlush(MessageFactory.getMessage(Constant.PING, Constant.PING_STR));
//        }
//    }
//}
