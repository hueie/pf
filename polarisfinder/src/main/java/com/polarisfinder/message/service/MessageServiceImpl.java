package com.polarisfinder.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.message.dao.MessageDAO;
import com.polarisfinder.message.entity.Message;
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO MessageDAO;
	
	@Override
	public synchronized boolean createMessage(Message Message){
		MessageDAO.createMessage(Message);
    	return true;
	}

	@Override
	public List<Message> getMessageById(int id, int paging) {
		return MessageDAO.getMessageById(id, paging);
	}
	
	@Override
	public synchronized boolean deleteMessage(Message Message){
		MessageDAO.deleteMessage(Message);
    	return true;
	}

	@Override
	public List<Message> getMessage(int id, int paging) {
		return MessageDAO.getMessage(id, paging);
	}
	@Override
	public List<Message> getMessageSent(int id, int paging) {
		return MessageDAO.getMessageSent(id, paging);
	}
	@Override
	public List<Message> getMessageStarred(int id, int paging) {
		return MessageDAO.getMessageStarred(id, paging);
	}
	@Override
	public void updateStarred(int id, boolean star) {
		MessageDAO.updateStarred(id, star);
	}
	@Override
	public Message viewMessage(int id) {
		return MessageDAO.viewMessage(id);
	}
	
	
}
