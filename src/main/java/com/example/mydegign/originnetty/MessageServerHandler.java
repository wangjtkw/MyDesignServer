//package com.example.mydegign.originnetty;
//
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.socket.SocketChannel;
//
//
//public class MessageServerHandler extends ChannelHandlerAdapter {
//
//    @Override
//    public void channelInactive(ChannelHandlerContext cxt) throws Exception {
//        System.out.println("channelInactive()...");
//        UserManager.getInstance().removeChannel((SocketChannel) cxt.channel());
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext cxt, Object obj) throws Exception {
//        System.out.println("channelRead()...  threadId: " + Thread.currentThread().getId());
//        MessageProto.Message message = (MessageProto.Message) obj;
//        switch (message.getMsgType()) {
//            case PING:
//                System.out.println("received ping..." + message.getClientID());
//                cxt.channel().writeAndFlush(createResponseMsg(message, MessageProto.Message.MessageType.PING, null));
//                break;
//            case LOGIN:
//                UserManager.getInstance().addUser(message.getGroupID(), message.getClientID(), (SocketChannel) cxt.channel());
//                int count = UserManager.getInstance().getGroupSize(message.getGroupID());
//                System.out.println("received login..." + message.getClientID() + ", count: " + count);
//                cxt.channel().writeAndFlush(createResponseMsg(message, MessageProto.Message.MessageType.LOGIN, "userCount:" + count));
//                System.out.println("received login sended..." + message.getClientID());
//                MessageManager.getInstance().putMessage((createResponseMsg(message, MessageProto.Message.MessageType.MESSAGE, "大家好! 我来了...")));
//                System.out.println("received login sended 222..." + message.getClientID());
//                cxt.channel().writeAndFlush(createResponseMsg(MessageProto.Message.newBuilder().setClientID("管理员").setGroupID(message.getGroupID()).setMsgType(MessageProto.Message.MessageType.MESSAGE).build(), MessageProto.Message.MessageType.MESSAGE, "欢迎 " + message.getClientID() + " 加入本群..."));
//                break;
//            case MESSAGE:
//                System.out.println("received message..." + message.getClientID() + "， " + message.getBody());
//                MessageManager.getInstance().putMessage(createResponseMsg(message, MessageProto.Message.MessageType.MESSAGE, null));
//                break;
//            case LOGOUT:
//                UserManager.getInstance().removeUser(message.getGroupID(), message.getClientID());
//                System.out.println("received logout..." + message.getClientID() + ", count: " + UserManager.getInstance().getGroupSize(message.getGroupID()));
//                MessageManager.getInstance().putMessage(createResponseMsg(message, MessageProto.Message.MessageType.MESSAGE, "大家聊! 我走了...."));
//                break;
//        }
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext cxt, Throwable cause) {
//        System.out.println("exceptionCaught()...");
//        UserManager.getInstance().removeChannel((SocketChannel) cxt.channel());
//        cause.printStackTrace();
//        cxt.close();
//    }
//
//    private MessageProto.Message createResponseMsg(MessageProto.Message receivedMsg, MessageProto.Message.MessageType type, String body) {
//        MessageProto.Message.Builder builder = MessageProto.Message.newBuilder();
//        builder.setClientID(receivedMsg.getClientID());
//        builder.setMsgType(type);
//        builder.setGroupID(receivedMsg.getGroupID());
//        if (body != null) {
//            builder.setBody(body);
//        } else {
//            builder.setBody(receivedMsg.getBody());
//        }
//        return builder.build();
//    }
//}
//
