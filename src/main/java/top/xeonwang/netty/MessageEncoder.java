package top.xeonwang.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.xeonwang.vo.MsgProtocol;

/**
 * @author Chen Q.
 */
public class MessageEncoder extends MessageToByteEncoder<MsgProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MsgProtocol msgProtocol, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(msgProtocol.getStep());
        byteBuf.writeInt(msgProtocol.getLength());
        byteBuf.writeBytes(msgProtocol.getContent());
    }
}
