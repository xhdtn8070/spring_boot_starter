package org.tikim.boot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
//    @Resource(name = "basicWebsocketChatHandler")
    @Resource(name = "basic2WebsocketChatHandler")
    private WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler,"/ws/chat").setAllowedOrigins("*");
    }

}