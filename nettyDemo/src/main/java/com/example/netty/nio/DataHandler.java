package com.example.netty.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * 服务端轮询客户端socket是否有发送数据
 */
public class DataHandler implements Runnable{
    List<SocketChannel> socketChannels;
    public DataHandler(List<SocketChannel> socketChannels) {
        this.socketChannels = socketChannels;
    }

    @Override
    public void run() {
        while(true){
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            for(SocketChannel socketChannel : socketChannels){
                int num = 0;
                try {
                    num = socketChannel.read(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(num > 0){
                    byteBuffer.flip();
                    byte [] bytes = new byte[byteBuffer.limit()];
                    byteBuffer.get(bytes);
                    System.out.println("Read data from "+socketChannel.socket().getRemoteSocketAddress()+"->"+new String(bytes));
                    byteBuffer.clear();

                    byteBuffer.put("Hello client".getBytes());
                    try {
                        socketChannel.write(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
