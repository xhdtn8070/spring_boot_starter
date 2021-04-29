package org.tikim.boot.domain.chat;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoomSimpleStomp extends ChatRoom{


    public ChatRoomSimpleStomp(String roomId, String name) {
        super(roomId, name);
    }

    public static ChatRoom create(String name){
        ChatRoom chatRoom = new ChatRoomSimpleStomp(
                UUID.randomUUID().toString(),
                name
        );
        return chatRoom;
    }



}
