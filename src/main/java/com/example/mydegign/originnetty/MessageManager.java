//package com.example.mydegign.originnetty;
//
//import io.netty.channel.socket.SocketChannel;
//
//import java.util.concurrent.*;
//
//public class MessageManager {
//    private static MessageManager manager;
//    private LinkedBlockingQueue<MessageProto.Message> mMessageQueue = new LinkedBlockingQueue<>();
//    private ThreadPoolExecutor mPoolExecutor = new ThreadPoolExecutor(5, 10, 15, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());
//
//    private MessageManager() {
//    }
//
//    public static MessageManager getInstance() {
//        if (manager == null) {
//            synchronized (MessageManager.class) {
//                if (manager == null) {
//                    manager = new MessageManager();
//                }
//            }
//        }
//        return manager;
//    }
//
//    public void putMessage(MessageProto.Message message) {
//        System.out.println("MessageManager-> putMessage()..." + message.getClientID() + ",  " + message.getBody());
//        try {
//            mMessageQueue.put(message);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void start() {
//        System.out.println("MessageManager-> start()... ");
//        while (true) {
//            try {
//                MessageProto.Message message = mMessageQueue.take();
//                mPoolExecutor.execute(new SendMessageTask(message));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                break;
//            } catch (RejectedExecutionException e) {
//                System.out.println("MessageManager-> 服务器消息队列已满...延时2妙后继续发送...");
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                continue;
//            }
//        }
//
//    }
//
//    public void stop() {
//        System.out.println("MessageManager-> stop()... ");
//        mMessageQueue.clear();
//        mPoolExecutor.shutdownNow();
//    }
//
//    class SendMessageTask implements Runnable {
//        private MessageProto.Message message;
//
//        public SendMessageTask(MessageProto.Message message) {
//            this.message = message;
//        }
//
//        @Override
//        public void run() {
//            if (message.getReceiveID().length() > 2) {
//                System.out.println("MessageManager-> sendMessage... to client: " + message.getReceiveID() + ",  " + message.getBody());
//                //发送单聊消息;
//                SocketChannel channel = UserManager.getInstance().getUserChannel(message.getReceiveID());
//                if (channel != null && channel.isActive()) {
//                    channel.writeAndFlush(message);
//                }
//            } else {
//                System.out.println("MessageManager-> sendMessage... to group: " + message.getGroupID() + ",  " + message.getBody());
//                //发送群聊消息;
//                CopyOnWriteArrayList<String> userList = UserManager.getInstance().getUserListInGroup(message.getGroupID());
//                for (String user : userList) {
//                    if (!user.equalsIgnoreCase(message.getClientID())) {
//                        SocketChannel channel = UserManager.getInstance().getUserChannel(user);
//                        if (channel != null && channel.isActive()) {
//                            channel.writeAndFlush(message);
//                        }
//                    }
//                }
//            }
//        }
//    }
//}