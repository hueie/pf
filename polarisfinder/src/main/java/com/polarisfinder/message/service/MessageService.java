package com.polarisfinder.message.service;

import java.util.List;

import com.polarisfinder.message.entity.Message;

public interface MessageService {
	boolean createMessage(Message Message);
	List<Message> getMessageById(int id, int paging);
	boolean deleteMessage(Message Message);
	
}
