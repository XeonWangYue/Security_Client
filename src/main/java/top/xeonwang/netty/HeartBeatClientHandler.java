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
    Integer waring = 0;

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
                log.debug(info.toString());
                byte[] content = ret.getBytes(StandardCharsets.UTF_8);
                log.debug(String.valueOf(content.length));
                msg.setContent(content);
                msg.setLength(content.length);
                log.info("心跳事件发送" + ctx.channel().remoteAddress());
                ctx.writeAndFlush(msg);
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MsgProtocol) {
            MsgProtocol p = (MsgProtocol) msg;
            String str = new String(p.getContent(), StandardCharsets.UTF_8);
            log.error(str);

            if (p.getStep() == 1) {
                log.info("断网警告");
            }
            if (p.getStep() == 2) {
                log.info("断网");
            }
        }
    }
}
