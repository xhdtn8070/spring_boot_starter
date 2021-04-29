package org.tikim.boot.controller.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tikim.boot.domain.chat.ChatMessage;

import javax.annotation.Resource;
import java.io.IOException;

@RestController("KafkaSampleController")
@RequestMapping(value = "/sample/kafka")
public class KafkaSampleController {
    @Resource
    KafkaTemplate<String,String> kafkaProducer;

    @GetMapping(value = "/test")
    public ResponseEntity<String> test() throws JsonProcessingException {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage("메시지");
        chatMessage.setRoomId("room-id");
        chatMessage.setSender("김통일");
        chatMessage.setType(ChatMessage.MessageType.TALK);
        kafkaProducer.send("TOPIC", new ObjectMapper().writeValueAsString(chatMessage));
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @KafkaListener(topics = "TOPIC", groupId = "test")
    public void consume(String message) throws IOException {
        System.out.println("스트링 : " + String.format("Consumed message : %s", message));
        ChatMessage chatMessage = new ObjectMapper().readValue(message,ChatMessage.class);
        System.out.println("클래스 : " + chatMessage.toString());
    }
}
