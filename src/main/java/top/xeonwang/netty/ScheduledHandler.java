package top.xeonwang.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.xeonwang.vo.MsgProtocol;

/**
 * @author Chen Q.
 */
public class ScheduledHandler extends SimpleChannelInboundHandler<MsgProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgProtocol msgProtocol) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }
}
