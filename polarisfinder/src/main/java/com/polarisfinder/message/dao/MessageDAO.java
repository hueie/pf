package com.polarisfinder.message.dao;

import java.util.List;

import com.polarisfinder.message.entity.Message;

public interface MessageDAO {

    void createMessage(Message Message);
	List<Message> getMessageById(int id, int paging);
	void deleteMessage(Message Message);
	
	
}
