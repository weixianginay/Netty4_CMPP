package com.netty4;

import com.netty4.Utils.MsgUtils;
import com.netty4.entity.CmppDeliver;
import com.netty4.entity.CmppSubmitResp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * 主要业务 handler
 * @author weixiang
 * @date 2018/8/2 15:37
 */

public class CMPPHandler extends SimpleChannelInboundHandler {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof CmppSubmitResp){
            CmppSubmitResp resp=(CmppSubmitResp)msg;
            System.out.println("-------------接收到短信提交应答-------------");
            System.out.println("----自增id："+resp.getMsgId());
            System.out.println("----状态："+ resp.getState());
            System.out.println("----第一次响应："+resp.getMsgId2());

        }

        if (msg instanceof CmppDeliver){
            CmppDeliver resp=(CmppDeliver)msg;
            // 是否为状态报告 0：非状态报告1：状态报告
            if (resp.getRegistered_Delivery() == 1) {
                // 如果是状态报告的话
                System.out.println("-------------状态报告---------------");
                System.out.println("----第二次响应："+resp.getMsg_Id_DELIVRD());
                System.out.println("----手机号："+resp.getDest_terminal_Id());
                System.out.println("----状态："+resp.getStat());
            } else {
                //用户回复会打印在这里
                System.out.println(""+ MsgUtils.bytesToLong(resp.getMsg_Id()));
                System.out.println(resp.getSrc_terminal_Id());
                System.out.println(resp.getMsg_Content());
            }
        }
    }

}
