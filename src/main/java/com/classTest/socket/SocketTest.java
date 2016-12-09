package com.classTest.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by root on 16-12-8.
 */
public class SocketTest {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 9080);

            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("client 0");

            DataInputStream in = new DataInputStream(client.getInputStream());
            System.out.println("收到:" + in.readUTF());

            out.close();
            in.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
