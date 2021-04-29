package org.tikim.boot.serviceImpl.chat;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import org.tikim.boot.domain.chat.ChatRoom;
import org.tikim.boot.domain.chat.ChatRoomSimpleStomp;
import org.tikim.boot.service.chat.ChatService;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class SimpleStompChatServiceImpl implements ChatService {

    private Map<String, ChatRoomSimpleStomp> chatRooms;

    @PostConstruct
    public void init() {
        chatRooms = new LinkedHashMap<>();
    }

    @Override
    public List<ChatRoom> findAllRoom() {
        //페이지네이션 적용 X
        List list = new ArrayList(chatRooms.values());
        Collections.reverse(list);
        return list;
    }

    @Override
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    @Override
    public ChatRoom createRoom(String name) {
        //성공 실패에 대한 처리 X
        ChatRoomSimpleStomp chatRoom = (ChatRoomSimpleStomp) ChatRoomSimpleStomp.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    @Override
    public <T> void sendMessage(WebSocketSession session, T message) {

    }
}
