package org.tikim.boot.webSocket.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.tikim.boot.domain.chat.ChatMessage;
import org.tikim.boot.domain.chat.ChatRoom;
import org.tikim.boot.domain.chat.ChatRoomSimpleWebSocket;
import org.tikim.boot.service.chat.ChatService;

@Component
public class Basic2WebsocketChatHandler extends TextWebSocketHandler {

    @Autowired
    @Qualifier("basicChatServiceImpl")
    private ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        try{
            ChatMessage chatMessage = new ObjectMapper().readValue(payload, ChatMessage.class);
            ChatRoomSimpleWebSocket room = (ChatRoomSimpleWebSocket) chatService.findRoomById(chatMessage.getRoomId());
            //방을 못찾았을때의 exception 처리 필요
            room.handleActions(session, chatMessage, chatService);
        }catch (Exception exception){
            System.out.println("json 형태 오류!");
            return;
        }

    }
}