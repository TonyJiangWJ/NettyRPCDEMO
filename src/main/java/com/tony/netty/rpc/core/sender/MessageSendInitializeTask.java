package com.tony.netty.rpc.core.sender;

import com.tony.netty.rpc.core.RpcServerLoader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Author jiangwj20966 on 2017/11/22.
 */
public class MessageSendInitializeTask implements Runnable {

    private EventLoopGroup eventLoopGroup = null;
    private InetSocketAddress serverAddress = null;
    private RpcServerLoader loader = null;

    public MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress serverAddress, RpcServerLoader loader) {
        this.eventLoopGroup = eventLoopGroup;
        this.serverAddress = serverAddress;
        this.loader = loader;
    }

    public void run() {
        Bootstrap b = new Bootstrap();
        b.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
        b.handler(new MessageSendChannelInitializer());

        ChannelFuture channelFuture = b.connect(serverAddress);
        channelFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    MessageSendHandler handler = channelFuture.channel().pipeline().get(MessageSendHandler.class);
                    MessageSendInitializeTask.this.loader.setMessageSendHandler(handler);
                } else {
                    System.err.println("获取连接失败" + serverAddress.getHostName() + ":" + serverAddress.getPort());
                }
            }

        });
    }
}
