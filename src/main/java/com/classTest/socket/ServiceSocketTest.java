package com.classTest.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by root on 16-12-8.
 */
public class ServiceSocketTest {
    public static void main(String[] args) {
        try {
            while (true) {
                ServerSocket serviceSocket = new ServerSocket(9080);
                System.out.println("返回此服务器套接字的本地地址 : "+serviceSocket.getInetAddress());
                System.out.println("返回此套接字在其上侦听的端口 : " + serviceSocket.getLocalPort());
                System.out.println("返回此套接字绑定的端点的地址，如果尚未绑定则返回 null : " + serviceSocket.getLocalSocketAddress());
                //serviceSocket.setReceiveBufferSize(10240);//为从此 ServerSocket 接受的套接字的 SO_RCVBUF 选项设置默认建议值
                System.out.println("获取此 ServerSocket 的 SO_RCVBUF 选项的值，该值是将用于从此 ServerSocket 接受的套接字的建议缓冲区大小 : " + serviceSocket.getReceiveBufferSize());
                //serviceSocket.setReuseAddress(false);//启用/禁用 SO_REUSEADDR 套接字选项
                System.out.println("测试是否启用 SO_REUSEADDR : " + serviceSocket.getReuseAddress());//SO_REUSEADDR是让端口释放后立即就可以被再次使用
                //serviceSocket.setSoTimeout(5000);//通过指定超时值启用/禁用 SO_TIMEOUT，以毫秒为单位
                System.out.println("获取 SO_TIMEOUT 的设置 : " + serviceSocket.getSoTimeout());//此选项表示等待客户连接的超时时间
                System.out.println("返回 ServerSocket 的绑定状态 : " + serviceSocket.isBound());
                System.out.println("返回 ServerSocket 的关闭状态 : " + serviceSocket.isClosed());
                Socket server = serviceSocket.accept();

                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println("收到:" + in.readUTF());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("服务端");

                out.close();
                in.close();
                server.close();
                serviceSocket.close();//关闭此套接字
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
