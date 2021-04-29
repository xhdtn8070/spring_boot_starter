package org.tikim.boot.controller.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tikim.boot.domain.chat.ChatMessage;
import org.tikim.boot.domain.chat.ChatRoom;
import org.tikim.boot.service.chat.ChatService;

@Controller
public class SimpleStompChatMessageController {
    @Autowired
    @Qualifier("simpleStompChatServiceImpl")
    private ChatService chatService;

//    @Autowired
//    private Configuration configuration;

    @Autowired
    SimpMessageSendingOperations messageSendingOperations;

    @MessageMapping("chat/message")
    public void message(ChatMessage message){
        System.out.println(message.getType());
        System.out.println(message.getSender());
        if(ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender()+"님이 입장하셨습니다.");
        messageSendingOperations.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }




}
