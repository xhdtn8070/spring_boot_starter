package org.tikim.boot.domain.chat;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ChatRoom {

    private String roomId;
    private String name;


    public ChatRoom(String roomId, String name) {
            this.roomId = roomId;
            this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
