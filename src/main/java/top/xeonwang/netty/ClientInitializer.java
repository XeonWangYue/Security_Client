package top.xeonwang.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Chen Q.
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new IdleStateHandler(0, 1, 0, TimeUnit.SECONDS));
        pipeline.addLast("Decoder", new MessageDecoder());
        pipeline.addLast("Encoder", new MessageEncoder());
        pipeline.addLast(new HeartBeatClientHandler());
    }
}
