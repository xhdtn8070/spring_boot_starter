package org.tikim.boot.serviceImpl.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.tikim.boot.domain.chat.ChatRoom;
import org.tikim.boot.domain.chat.ChatRoomSimpleWebSocket;
import org.tikim.boot.service.chat.ChatService;

import java.io.IOException;
import java.util.*;

@Service
public class DBChatServiceImpl implements ChatService {

    private Map<String, ChatRoom> chatRooms;

    @Override
    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    @Override
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    @Override
    public ChatRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom  chatRoom = new ChatRoomSimpleWebSocket(randomId,name);
        chatRooms.put(randomId,chatRoom);
        return chatRoom;
    }

    @Override
    public <T> void sendMessage(WebSocketSession session, T message)  {
        try {
            session.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(message)));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
