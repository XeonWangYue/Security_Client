package top.xeonwang.netty;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import top.xeonwang.vo.MsgProtocol;
import top.xeonwang.vo.SystemInfoVO;

import java.nio.charset.StandardCharsets;

/**
 * @author Chen Q.
 */
@Slf4j
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                MsgProtocol msg = new MsgProtocol();
                msg.setStep(0);
                ObjectMapper mapper = new ObjectMapper();
                SystemInfoVO info = SystemInfoVO.toSystemInfo();
                String ret = mapper.writeValueAsString(info);
                log.info(info.toString());
                byte[] content = ret.getBytes(StandardCharsets.UTF_8);
                log.info(String.valueOf(content.length));
                msg.setContent(content);
                msg.setLength(content.length);
                log.info("心跳事件发送" + ctx.channel().remoteAddress());
                ctx.writeAndFlush(msg);
            }
        }
    }
}
