//package com.example.mydegign.originnetty.client;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.timeout.IdleStateEvent;
//import io.netty.util.ReferenceCountUtil;
//
//public class NettyClientHandler extends SimpleChannelInboundHandler<MessageProto.Message> {
//    private String TAG = "ChatClient";
//    private int pingCount = 0;
//
//    @Override
//    public void userEventTriggered(ChannelHandlerContext cxt, Object event) throws Exception {
//        Log.d(TAG, "userEventTriggered()...");
//        if (event instanceof IdleStateEvent) {
//            IdleStateEvent e = (IdleStateEvent) event;
//            switch (e.state()) {
//                case ALL_IDLE:
//                    cxt.writeAndFlush(NettyClient.getInstance().createMessage(Message.MessageType.PING, null));
//                    pingCount++;
//                    Log.d(TAG, "send ping to server...");
//                    if(pingCount > 3){
//                        Log.d(TAG, "heart timeout, so disconnect...");
//                        cxt.close();
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    @Override
//    protected void messageReceived(ChannelHandlerContext channelHandlerContext, MessageProto.Message message) throws Exception {
//        Log.d(TAG, "messageReceived()...");
//        switch(message.getMsgType()){
//            case PING:
//                pingCount = 0;
//                break;
//            case MESSAGE:
//                Log.d(TAG, "Received message: " + message.getClientID() + ",  " + message.getBody());
//                EventBus.getDefault().post(message);
//                break;
//        }
//        ReferenceCountUtil.release(message);
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext cxt, Throwable cause) {
//        Log.d(TAG, "messageReceived()...");
//        cause.printStackTrace();
//        cxt.close();
//    }
//}
