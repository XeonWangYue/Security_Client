package top.xeonwang;

import com.sun.management.OperatingSystemMXBean;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import top.xeonwang.netty.ClientInitializer;
import top.xeonwang.vo.SystemInfoVO;

import java.lang.management.ManagementFactory;

/**
 * @author Chen Q.
 */
@Slf4j
public class Client {
    static String ip = "192.168.17.140";
    static int port = 8090;

    public static void main(String[] args) {
        EventLoopGroup eventGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_SNDBUF, 1024 * 20)
                .option(ChannelOption.SO_RCVBUF, 1024 * 20)
                .handler(new ClientInitializer());
        ChannelFuture cf = null;
        try {
            log.info("连接服务器中..." + ip);
            cf = bootstrap.connect(ip, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        while (true) {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info(SystemInfoVO.getSystemInfo().toString());
//        }
    }
}
