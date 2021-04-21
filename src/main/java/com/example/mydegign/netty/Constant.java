package com.example.mydegign.netty;

import io.netty.channel.ChannelHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "constant")
public class Constant {
    public static final String SUCCESS = "SUCCESS";

    public static final String PING = "ping";

    public static final String PING_STR = "ping_str";
    public static final String PONG_STR = "pong_str";

    public static final String OK_STR = "ok_str";

    public static final String USER_LOGIN = "user_login";

    public static final String EMPLOYER_LOGIN = "employer_login";

    public static final String USER2EMPLOYER_MESSAGE = "user2employer_message";
    public static final String EMPLOYER2USER_MESSAGE = "employer2user_message";
}
