package com.polarisfinder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import com.polarisfinder.chatroom.controller.ChatroomHandler;
import com.polarisfinder.fileupload.controller.MyWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig  implements WebSocketConfigurer{
	//https://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html
	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/bigfileupload");
        registry.addHandler(chatroomHandler(), "/chatroom");
    }

    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler();
    }

    @Bean
    public WebSocketHandler chatroomHandler() {
        return new ChatroomHandler();
    }
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);//8192
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }
}
