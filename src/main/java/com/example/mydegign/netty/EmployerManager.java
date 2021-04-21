package com.example.mydegign.netty;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmployerManager {
    private static EmployerManager manager;
    private static final Map<Integer, Channel> employer2channel = new ConcurrentHashMap<>();
    private static final Map<Channel, Integer> channel2employer = new ConcurrentHashMap<>();

    private EmployerManager() {
    }

    public static EmployerManager getInstance() {
        if (manager == null) {
            synchronized (EmployerManager.class) {
                if (manager == null) {
                    manager = new EmployerManager();
                }
            }
        }
        return manager;
    }


    public void addEmployer(int clientID, Channel channel) {
        employer2channel.put(clientID, channel);
        channel2employer.put(channel, clientID);
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

    public Channel getEmployerChannel(int clientID) {
        return employer2channel.get(clientID);
    }

    public Boolean isOnLine(int userId) {
        System.out.println("user size:" + employer2channel.size());
        return employer2channel.containsKey(userId);
    }

    public int getEmployerId(Channel channel) {
        return channel2employer.get(channel);
    }

    public void removeEmployer(int clientID) {
        System.out.println("removeUser()... " + clientID);
        Channel channel = employer2channel.get(clientID);
        employer2channel.remove(clientID);
        channel2employer.remove(channel);
    }

    public void removeChannel(Channel channel) {
        int clientID = channel2employer.get(channel);
        System.out.println("removeUser()... " + clientID);
        employer2channel.remove(clientID);
        channel2employer.remove(channel);
    }

    public int getTotalEmployerCount() {
        return employer2channel.size();
    }

    public void clearAll() {
        employer2channel.clear();
        channel2employer.clear();
    }

}
