package org.tikim.boot.domain.chat;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.socket.WebSocketSession;
import org.tikim.boot.service.chat.ChatService;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoomSimpleWebSocket extends ChatRoom{


    @JsonIgnore
    private Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoomSimpleWebSocket(String roomId, String name) {
        super(roomId, name);
    }


    public synchronized void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }

}
