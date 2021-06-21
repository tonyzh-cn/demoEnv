package com.example.netty.nio;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * 服务端轮询是否有客户端建立新的连接
 */
public class ConnectHandler implements Runnable{
    ServerSocketChannel serverSocketChannel;
    List<SocketChannel> socketChannels;
    public ConnectHandler(ServerSocketChannel serverSocketChannel, List<SocketChannel> socketChannels) {
        this.serverSocketChannel = serverSocketChannel;
        this.socketChannels = socketChannels;
    }

    @Override
    public void run() {
        while(true) {
            SocketChannel newSocketChannel = null;
            try {
                newSocketChannel = serverSocketChannel.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (newSocketChannel != null) {
                try {
                    newSocketChannel.configureBlocking(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Receive a new connection from " + newSocketChannel.socket().getRemoteSocketAddress());
                socketChannels.add(newSocketChannel);
            }
        }
    }
}
