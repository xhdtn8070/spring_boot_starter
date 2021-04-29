package org.tikim.boot.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tikim.boot.domain.chat.ChatRoom;
import org.tikim.boot.service.chat.ChatService;

@RestController
@RequestMapping(value = "/chat/basic")
public class BasicChatController {
    @Autowired
    @Qualifier("basicChatServiceImpl")
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatRoom> createRoom(@RequestParam String name){
        ChatRoom room = chatService.createRoom(name);
        return new ResponseEntity<ChatRoom>(room, HttpStatus.CREATED);
    }
}
