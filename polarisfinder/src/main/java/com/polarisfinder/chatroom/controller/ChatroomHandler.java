package com.polarisfinder.chatroom.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class ChatroomHandler extends AbstractWebSocketHandler {
	
	//@Autowired
	//ServletContext context;

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
    private String animalName;
    private LinkedList<String> animalList = new LinkedList<String>();
    
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//String sessionName = session.getPrincipal().getName();
		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage(this.animalName+"|"+message.getPayload()));
			//sess.sendMessage(new TextMessage(sessionName+"|"+message.getPayload()));
		}
    }
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Scanner sc = new Scanner(new File("/txt/animals.txt"));
		this.animalName = sc.nextLine();
		//String sessionName = session.getPrincipal().getName();
		for(int i=0; i<sessionList.size(); i++) {
			//참가자 초기화
			session.sendMessage(new TextMessage("add"+"|"+animalList.get(i)));
			//session.sendMessage(new TextMessage("add"+"|"+sessionList.get(i).getPrincipal().getName()));
		}
		animalList.add(this.animalName);
		sessionList.add(session);
		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage("add"+"|"+this.animalName));
			//sess.sendMessage(new TextMessage("add"+"|"+sessionName));
		}
		System.out.println("채팅방 입장자: "+this.animalName);
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("error.......");
		exception.printStackTrace();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//String sessionName = session.getPrincipal().getName();
		//CurrentUser currentUser = (CurrentUser) session.getPrincipal();
		animalList.remove(this.animalName);
		sessionList.remove(session);
		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage("del"+"|"+this.animalName));
			//sess.sendMessage(new TextMessage("del"+"|"+sessionName));
		}
		System.out.println("채팅방 퇴장자: "+this.animalName);
	}
}
