package com.example.mydegign.netty;

import com.google.protobuf.ByteString;

public class MessageFactory {

    public static MessageProto.Message getMessage(String type, String body) {
        MessageProto.Message.Builder builder = MessageProto.Message.newBuilder();
        builder.setMsgType(type).setBody(body);
        return builder.build();
    }

    public static MessageProto.Message getMessage(String type, int clientId, String body) {
        MessageProto.Message.Builder builder = MessageProto.Message.newBuilder();
        builder.setMsgType(type).setClientID(clientId).setBody(body);
        return builder.build();
    }

    public static MessageProto.Message getMessage(String type, int clientId, int receiveId, String body) {
        MessageProto.Message.Builder builder = MessageProto.Message.newBuilder();
        builder.setMsgType(type).setClientID(clientId).setReceiveId(receiveId).setBody(body);
        return builder.build();
    }
}
