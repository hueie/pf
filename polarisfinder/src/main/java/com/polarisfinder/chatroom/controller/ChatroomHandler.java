package com.polarisfinder.chatroom.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.polarisfinder.user.entity.CurrentUser;

public class ChatroomHandler extends AbstractWebSocketHandler {
	
	//@Autowired
	//ServletContext context;

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
    
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String sessionName = session.getPrincipal().getName();
		//System.out.println("Session Name : "+sessionName);
		//CurrentUser currentUser = (CurrentUser) session.getPrincipal();
		//CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage(sessionName+"|"+message.getPayload()));
		}
    }
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionName = session.getPrincipal().getName();
		//CurrentUser currentUser = (CurrentUser) session.getPrincipal();
		for(int i=0; i<sessionList.size(); i++) {
			session.sendMessage(new TextMessage("add"+"|"+sessionList.get(i).getPrincipal().getName()));
		}
		sessionList.add(session);
		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage("add"+"|"+sessionName));
		}
		System.out.println("채팅방 입장자: "+sessionName);
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("error.......");
		exception.printStackTrace();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String sessionName = session.getPrincipal().getName();
		//CurrentUser currentUser = (CurrentUser) session.getPrincipal();
		sessionList.remove(session);
		for(WebSocketSession sess : sessionList){
			
			sess.sendMessage(new TextMessage("del"+"|"+sessionName));
		}
		System.out.println("채팅방 퇴장자: "+sessionName);
	}
}
