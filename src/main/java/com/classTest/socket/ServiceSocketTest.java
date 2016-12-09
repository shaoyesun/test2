package com.classTest.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by root on 16-12-8.
 */
public class ServiceSocketTest {

    public static void main(String[] args) {
        try {
            while (true) {
                ServerSocket serviceSocket = new ServerSocket(9080);
                Socket server = serviceSocket.accept();

                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println("收到:" + in.readUTF());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("server");

                out.close();
                in.close();
                server.close();
                serviceSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
