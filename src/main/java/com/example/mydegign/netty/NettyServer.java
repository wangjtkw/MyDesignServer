package com.example.mydegign.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class NettyServer implements
        ApplicationRunner,
        ApplicationListener<ContextClosedEvent>,
        ApplicationContextAware {

    @Value("${netty.websocket.port}")
    private int port;

    @Value("${netty.websocket.ip}")
    private String ip;

    @Value("${netty.websocket.max-frame-size}")
    private long maxFrameSize;

    private ApplicationContext applicationContext;

    private Channel serverChannel;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //server端引导类
        //bossGroup的线程数
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        //连接池处理数据
        //worker的线程数
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.localAddress(port);
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    //长连接定义队列大小
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //开启长连接
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    //日志打印设置
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //设置childHandler执行所有的连接请求
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //用户每次请求都会从第一个Handler开始
                            //decoder
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            ch.pipeline().addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
                            //encoder
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
                            //设置超时时间
                            ch.pipeline().addLast(new IdleStateHandler(150, 150, 300, TimeUnit.SECONDS));
                            //心跳续约
                            ch.pipeline().addLast(new HeartBeatRespHandler());
                            //登录握手
                            ch.pipeline().addLast(new UserLoginAuthRespHandler());
                            ch.pipeline().addLast(new EmployerLoginAuthRespHandler());
                            ch.pipeline().addLast(new ChatRespHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            serverChannel = future.channel();
//            System.out.println("server: start ok ! host:" + ip + ",port:" + port);
            serverChannel.closeFuture().sync();
        } finally {
            //优雅退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        if (this.serverChannel != null) {
            this.serverChannel.close();
        }
    }
}
