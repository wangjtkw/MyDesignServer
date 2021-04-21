package com.example.mydegign.netty;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class UserManager {
    private static UserManager manager;
    private static final Map<Integer, Channel> user2channel = new ConcurrentHashMap<>();
    private static final Map<Channel, Integer> channel2user = new ConcurrentHashMap<>();

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (manager == null) {
            synchronized (UserManager.class) {
                if (manager == null) {
                    manager = new UserManager();
                }
            }
        }
        return manager;
    }


    public void addUser(int clientID, Channel channel) {
        user2channel.put(clientID, channel);
        channel2user.put(channel, clientID);
//        if (groupList.get(groupID) == null) {
//            System.out.println("addUser()... create new group-> " + groupID + ",  " + clientID);
//            CopyOnWriteArrayList<String> users = new CopyOnWriteArrayList<String>();
//            users.add(clientID);
//            groupList.put(groupID, users);
//        } else {
//            System.out.println("addUser()... " + groupID + ",  " + clientID);
//            groupList.get(groupID).add(clientID);
//        }
    }

    public Channel getUserChannel(int clientID) {
        return user2channel.get(clientID);
    }

    public Boolean isOnLine(int userId) {
        System.out.println("user size:" + user2channel.size());
        return user2channel.containsKey(userId);
    }

    public int getUserId(Channel channel) {
        return channel2user.get(channel);
    }

    public void removeUser(int clientID) {
        System.out.println("removeUser()... " + clientID);
        Channel channel = user2channel.get(clientID);
        user2channel.remove(clientID);
        channel2user.remove(channel);
    }

    public void removeChannel(Channel channel) {
        int clientID = channel2user.get(channel);
        System.out.println("removeUser()... " + clientID);
        user2channel.remove(clientID);
        channel2user.remove(channel);
    }

    public int getTotalUserCount() {
        return user2channel.size();
    }

    public void clearAll() {
        user2channel.clear();
        channel2user.clear();
    }
}
