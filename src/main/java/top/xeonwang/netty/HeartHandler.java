package top.xeonwang.netty;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import top.xeonwang.vo.MsgProtocol;
import top.xeonwang.vo.SystemInfoVO;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Chen Q.
 */
@Slf4j
public class HeartHandler extends SimpleChannelInboundHandler<MsgProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgProtocol msgProtocol) throws Exception {
        if (msgProtocol.getStep() == 0) {
            ByteBuf buf = Unpooled.buffer();

        }
        if (msgProtocol.getStep() == 1) {
            log.error("检测到异常流量，请停止！");
        }
    }
}
