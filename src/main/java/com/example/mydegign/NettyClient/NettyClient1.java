//package com.example.mydegign.NettyClient;
//
//import com.example.mydegign.netty.MessageProto;
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.protobuf.ProtobufDecoder;
//import io.netty.handler.codec.protobuf.ProtobufEncoder;
//import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
//import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.LoggingHandler;
//import io.netty.handler.timeout.IdleStateHandler;
//
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
//public class NettyClient1 {
//    EventLoopGroup group = new NioEventLoopGroup();
//    private static final int MAX_RETRY = 5; //最大重连次数
//    private static Channel channel;
//
//
//    public void connect(int port, String host) {
//
//        //client NIO thread
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(group).channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY, true)
//                    //日志打印
//                    .handler(new LoggingHandler(LogLevel.INFO))
//                    .handler(new ChannelInitializer<Channel>() {
//                        protected void initChannel(Channel ch) throws Exception {
//                            //设置超时时间
//                            ch.pipeline().addLast(new IdleStateHandler(10, 10, 10, TimeUnit.SECONDS));
//                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
//                            ch.pipeline().addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
//                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
//                            ch.pipeline().addLast(new ProtobufEncoder());
//                            //心跳续约
//                            ch.pipeline().addLast(new HeartBeatReqHandler());
//                            //登录校验
//                            ch.pipeline().addLast(new LoginAuthReqHandler1());
//                            ch.pipeline().addLast(new ChatReqHandler());
//                        }
//                    });
//            //异步连接
//            connect(b, host, port, MAX_RETRY);
//        } finally {
//            //优雅退出
//            group.shutdownGracefully();
//        }
//    }
//
//    public static void main(String[] args) {
//        new NettyClient1().connect(1024, "0.0.0.0");
//    }
//
//    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
//        ChannelFuture future1 = bootstrap.connect(host, port).addListener(future -> {
//            if (future.isSuccess()) {
//                System.out.println(new Date() + ": 连接成功!");
//            } else if (retry == 0) {
//                System.out.println("重试次数已用完，放弃连接！");
//            } else {
//                // 第几次重连
//                int order = (MAX_RETRY - retry) + 1;
//                // 本次重连的间隔
//                int delay = 1 << order;
//                System.out.println(new Date() + ": 连接失败，第" + order + "次重连");
//                bootstrap.group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
//            }
//        });
//        try {
//            future1.channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
