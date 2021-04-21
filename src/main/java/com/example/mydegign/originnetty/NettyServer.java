//package com.example.mydegign.originnetty;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.protobuf.ProtobufDecoder;
//import io.netty.handler.codec.protobuf.ProtobufEncoder;
//import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
//import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
//
//public final class NettyServer {
//    public static int PORT = 10000;
//    public final int HEART_SYNC_TIME = 300;
//    private EventLoopGroup bossGroup;
//    private EventLoopGroup workerGroup;
//
//    public void bind(int port) throws InterruptedException {
//        bossGroup = new NioEventLoopGroup(1);
//        workerGroup = new NioEventLoopGroup();
//        ServerBootstrap bootstrap = new ServerBootstrap();
//        try {
//            bootstrap.group(bossGroup, workerGroup);
//            bootstrap.channel(NioServerSocketChannel.class);
//            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
//            bootstrap.option(ChannelOption.TCP_NODELAY, true);
//            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
//            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    ChannelPipeline pipe = socketChannel.pipeline();
//                    pipe.addLast(new ProtobufVarint32FrameDecoder());
//                    pipe.addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
//                    pipe.addLast(new ProtobufVarint32LengthFieldPrepender());
//                    pipe.addLast(new ProtobufEncoder());
//                    pipe.addLast(new MessageServerHandler());
//                }
//            });
//            // Bind and start to accept incoming connections.
//            ChannelFuture f = bootstrap.bind(port).sync();
//
//
//            if (f.isSuccess()) {
//                System.out.println("server start success... port: " + port + ", main work thread: " + Thread.currentThread().getId());
//            }
//
//
//            // Wait until the server socket is closed.
//            f.channel().closeFuture().sync();
//
//        } finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//    }
//
//    public synchronized void stop() {
//        bossGroup.shutdownGracefully();
//        workerGroup.shutdownGracefully();
//    }
//
//    public static void main(String[] args) {
//        NettyServer server = null;
//        try {
//            server = new NettyServer();
//            if (args != null && args.length > 0 && args[0].length() > 3) {
//                PORT = Integer.parseInt(args[0]);
//            }
//
//            //message work thread.
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    MessageManager.getInstance().start();
//                }
//            }).start();
//
//            server.bind(PORT);
//        } catch (InterruptedException e) {
//            MessageManager.getInstance().stop();
//            UserManager.getInstance().clearAll();
//            server.stop();
//            e.printStackTrace();
//        }
//    }
//}