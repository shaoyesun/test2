package com.controller.websocket;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by root on 16-10-26.
 */
public class WebSocketHander implements WebSocketHandler {

    private static Logger logger = Logger.getLogger(WebSocketHander.class);

    private static int count = 0;//统计建立管道数

    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();

    //初次链接成功执行
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("链接成功......");
        users.add(session);
        String userName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        if (userName != null) {
            count++;
            session.sendMessage(new TextMessage(count + ""));
        }
    }

    //接受消息处理消息
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        sendMessageToUsers(new TextMessage(webSocketMessage.getPayload() + ""));
        //sendMessageToUser("123", new TextMessage(webSocketMessage.getPayload() + ""));
    }

    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        logger.debug("链接出错，关闭链接......");
        count--;
        users.remove(webSocketSession);
    }

    //关闭或离开此页面管道关闭
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.debug("链接关闭......" + closeStatus.toString());
        count--;
        users.remove(webSocketSession);
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        ArrayList<WebSocketSession> us = users;
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
