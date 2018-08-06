package com;

import com.netty4.NettyClient;
import com.netty4.Utils.Command;
import com.netty4.Utils.MsgUtils;
import com.netty4.entity.CmppMessageHeader;
import com.netty4.entity.CmppSubmit;

/**
 * @author weixiang
 * @date 2018/8/6 16:31
 */
public class Main {

    //ip
    public static String host = "39.106.108.70";
    //端口
    public static int port = 7890;
    //账号
    public static String serviceId = "801xxx";
    //密码
    public static String pwd = "xxxxxx";

    public static String srcId = "106902202xxx";

    public static void main(String[] args) {

        NettyClient client= new NettyClient(host,port,serviceId,pwd);

        client.start();

        String mobile="18501957080";

        String content="验证码8888";

        int sequenceId =  MsgUtils.getSequence();

        CmppMessageHeader submit=new CmppSubmit(Command.CMPP2_VERSION,serviceId,srcId, sequenceId,mobile,content);

        client.submit(submit);
    }
}
