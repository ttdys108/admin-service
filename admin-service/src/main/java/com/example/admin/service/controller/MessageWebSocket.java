package com.example.admin.service.controller;

import com.alibaba.fastjson.JSON;
import com.example.admin.data.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@ServerEndpoint("/message/{username}")
public class MessageWebSocket {

    private String username;
    private static final Map<String, Session> clients = new HashMap<>();
    //test username xuer tmp

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        log.info("open socket, user<{}>", username);
        clients.put(username, session);
        this.username = username;
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("<{}>消息报错，", username, throwable);
        clients.remove(username);
    }

    @OnClose
    public void onClose() {
        clients.remove(username);
        log.info("user socket closed: <{}>", username);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("accept msg: <{}>", message);
        MessageVO msg = JSON.parseObject(message, MessageVO.class);
        Session client = clients.get(msg.getTo());
        if(client != null) {//send
            log.info("send to client<{}>", msg.getTo());
            client.getAsyncRemote().sendText(message);
        }
    }

}
