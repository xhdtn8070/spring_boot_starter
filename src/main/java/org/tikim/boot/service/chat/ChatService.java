package org.tikim.boot.service.chat;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.socket.WebSocketSession;
import org.tikim.boot.domain.chat.ChatRoom;

import javax.annotation.PostConstruct;
import java.util.List;

public interface ChatService {

    public List<ChatRoom> findAllRoom();

    public ChatRoom findRoomById(String roomId);

    public ChatRoom createRoom(String name);

    public <T> void sendMessage(WebSocketSession session, T message);

}
