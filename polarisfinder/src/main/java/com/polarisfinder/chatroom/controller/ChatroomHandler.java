package com.polarisfinder.chatroom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.polarisfinder.animals.entity.Animals;
import com.polarisfinder.animals.service.AnimalsService;

public class ChatroomHandler extends AbstractWebSocketHandler {
	
	@Autowired
	private AnimalsService AnimalsService;
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	Map<String, String> sessionMap = new HashMap<String, String>();

	//private String animalName;
    private List<String> animalList = new ArrayList<String>();
    
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//String sessionName = session.getPrincipal().getName();
		for(WebSocketSession sess : sessionList){
			if(sess.isOpen()){
				sess.sendMessage(new TextMessage(message.getPayload()));
			}
			//sess.sendMessage(new TextMessage(sessionName+"|"+message.getPayload()));
		}
		/*
		Iterator<String> iterator = sessionMap.keySet().iterator();
	    while (iterator.hasNext()) {
	    	String key = (String) iterator.next();
	        System.out.print("key="+key);
	        System.out.println(" value="+sessionMap.get(key));
	    }
	    */
    }
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionName = session.getPrincipal().getName();
		if(!sessionMap.containsKey(sessionName)){
			List<Animals> anis = AnimalsService.getRandomAnimals();
			Animals ani = anis.get(0);
			String animalName = ani.getAnimals_nm();
			session.sendMessage(new TextMessage("init"+"|"+animalName));
			
			sessionMap.put(sessionName, animalName);
			for(int i=0; i<sessionList.size(); i++) {
				//참가자 초기화
				session.sendMessage(new TextMessage("add"+"|"+animalList.get(i)));
				//session.sendMessage(new TextMessage("add"+"|"+sessionList.get(i).getPrincipal().getName()));
			}
			animalList.add(animalName);
			sessionList.add(session);
			for(WebSocketSession sess : sessionList){
				if(sess.isOpen()){
					sess.sendMessage(new TextMessage("add"+"|"+animalName));
				}
				//sess.sendMessage(new TextMessage("add"+"|"+sessionName));
			}
			
			System.out.println("채팅방 입장자: "+animalName);
		} else{
			for(int i=0; i<sessionList.size(); i++) {
				//참가자 초기화
				session.sendMessage(new TextMessage("add"+"|"+animalList.get(i)));
				//session.sendMessage(new TextMessage("add"+"|"+sessionList.get(i).getPrincipal().getName()));
			}
		}
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		session.close();/*
		String sessionName = session.getPrincipal().getName();
		if(sessionMap.containsKey(sessionName)){
			String animalName = sessionMap.get(sessionName);
			animalList.remove(animalName);
			sessionList.remove(session);
			
			sessionMap.remove(sessionName);
			for(WebSocketSession sess : sessionList){
				sess.sendMessage(new TextMessage("del"+"|"+animalName));
				//sess.sendMessage(new TextMessage("del"+"|"+sessionName));
			}

			System.out.println("채팅방 퇴장자: "+animalName);
		}
		*/
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String sessionName = session.getPrincipal().getName();
		if(sessionMap.containsKey(sessionName)){
			String animalName = sessionMap.get(sessionName);
			animalList.remove(animalName);
			sessionList.remove(session);
			sessionMap.remove(sessionName);
			for(WebSocketSession sess : sessionList){
				if(sess.isOpen()){
					sess.sendMessage(new TextMessage("del"+"|"+animalName));
				}
				//sess.sendMessage(new TextMessage("del"+"|"+sessionName));
			}

			System.out.println("채팅방 퇴장자: "+animalName);
		}
	}
}
