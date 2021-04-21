//package com.example.mydegign.originnetty.client;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.protobuf.ProtobufDecoder;
//import io.netty.handler.codec.protobuf.ProtobufEncoder;
//import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
//import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
//import io.netty.handler.timeout.IdleStateHandler;
//
//public class NettyClient {
//    private String TAG = "ChatClient";
//    private static NettyClient client;
//    private EventLoopGroup eventLoopGroup;
//    private SocketChannel socketChannel;
//    private String clientID;
//    private String groupID;
//    private final int HEART_PING_TIME = 180;
//
//
//    private NettyClient() {
//    }
//
//    public static NettyClient getInstance() {
//        if (client == null) {
//            synchronized (NettyClient.class) {
//                if (client == null) {
//                    client = new NettyClient();
//                }
//            }
//        }
//        return client;
//    }
//
//
//    public void connect(String serverIP, int port) {
//        eventLoopGroup = new NioEventLoopGroup();
//        try {
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.channel(NioSocketChannel.class);
//            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
//            bootstrap.group(eventLoopGroup);
//            bootstrap.remoteAddress(serverIP, port);
//            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    socketChannel.pipeline().addLast(new IdleStateHandler(0, 0, HEART_PING_TIME));
//                    socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
//                    socketChannel.pipeline().addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
//                    socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
//                    socketChannel.pipeline().addLast(new ProtobufEncoder());
//                    socketChannel.pipeline().addLast(new NettyClientHandler());
//                }
//            });
//            ChannelFuture future = bootstrap.connect(serverIP, port).sync();
//            if (future.isSuccess()) {
//                socketChannel = (SocketChannel) future.channel();
//                Log.d(TAG, "connect server  success...");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            eventLoopGroup.shutdownGracefully();
//        }
//    }
//
//
//    public synchronized void sendMessage(MessageProto.Message message) {
//        Log.d(TAG, "sendMessage()..." + message.getBody());
//        socketChannel.writeAndFlush(message);
//    }
//
//
//    public void sync() throws InterruptedException {
//        socketChannel.closeFuture().sync();
//    }
//
//
//    public synchronized void stop() {
//        socketChannel.writeAndFlush(createMessage(MessageType.LOGOUT, null));
//        eventLoopGroup.shutdownGracefully();
//    }
//
//
//    public void setClientID(String clientID) {
//        this.clientID = clientID;
//    }
//
//    public String getClientID() {
//        return this.clientID;
//    }
//
//
//    public void setGroupID(String groupID) {
//        this.groupID = groupID;
//    }
//
//
//    public String getGroupID() {
//        return this.groupID;
//    }
//
//
//    public Message createMessage(MessageType type, String body) {
//        MessageProto.Message.Builder builder = MessageProto.Message.newBuilder();
//        builder.setClientID(clientID);
//        builder.setMsgType(type);
//        builder.setGroupID(groupID);
//        if (body != null) {
//            builder.setBody(body);
//        }
//        return builder.build();
//    }
//}
