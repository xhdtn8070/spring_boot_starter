package org.tikim.boot.controller.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tikim.boot.domain.chat.ChatRoom;
import org.tikim.boot.service.chat.ChatService;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/chat")
public class SimpleStompChatController {
    @Autowired
    @Qualifier("simpleStompChatServiceImpl")
    private ChatService chatService;

//    @Autowired
//    private Configuration configuration;


    @PostMapping
    public ResponseEntity<ChatRoom> createRoom(@RequestParam String name){
        ChatRoom room = chatService.createRoom(name);
        return new ResponseEntity<ChatRoom>(room, HttpStatus.CREATED);
    }

    @GetMapping(value="")
    public String rooms() throws JsonProcessingException {
//        try{
//            Template template = configuration.getTemplate("chat/room.ftl");
//
//            StringWriter templateStringWriter = new StringWriter();
//            template.process(null,templateStringWriter);
//            String result = templateStringWriter.toString();
//
//            return new ResponseEntity<String>(result,HttpStatus.OK).toString();
//        }catch (Exception e){
//            System.out.println(new ObjectMapper().writeValueAsString(e.getMessage()));
//            return new ResponseEntity<String>("ERROR",HttpStatus.OK).toString();
//        }
        return "/chat/room";

    }
    @GetMapping("room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) throws JsonProcessingException {
        model.addAttribute("roomId",roomId);

//        try{
//            Template template = configuration.getTemplate("chat/room.ftl");
//
//            StringWriter templateStringWriter = new StringWriter();
//            template.process(null,templateStringWriter);
//            String result = templateStringWriter.toString();
//
//            return new ResponseEntity<String>(result,HttpStatus.OK).toString();
//        }catch (Exception e){
//            System.out.println(new ObjectMapper().writeValueAsString(e.getMessage()));
//            return new ResponseEntity<String>("ERROR",HttpStatus.OK).toString();
//        }
        return "/chat/roomdetail";

    }


}
