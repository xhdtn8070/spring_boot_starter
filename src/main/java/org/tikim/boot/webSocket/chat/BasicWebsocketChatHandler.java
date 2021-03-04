package org.tikim.boot.webSocket.chat;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class BasicWebsocketChatHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("payLoad : " + payload);
        TextMessage textMessage = new TextMessage("Welcome Chatting Server!");
        session.sendMessage(textMessage);
    }
}