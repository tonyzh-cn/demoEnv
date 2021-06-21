package com.example.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class NioTimeServer {
    private static List<SocketChannel> socketChannels = new ArrayList<>();
    private static ServerSocketChannel serverSocketChannel;
    private final static int port = 8080;

    public static void main(String[] args) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);

        new Thread(new ConnectHandler(serverSocketChannel,socketChannels)).start();

        new Thread(new DataHandler(socketChannels)).start();

    }
}
