package com.polarisfinder.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.notice.dao.NoticeDAO;
import com.polarisfinder.notice.entity.Notice;
@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO NoticeDAO;
	
	@Override
	public synchronized boolean createNotice(Notice Notice){
		NoticeDAO.createNotice(Notice);
    	return true;
	}

	@Override
	public List<Notice> getNoticeById(int id, int paging) {
		return NoticeDAO.getNoticeById(id, paging);
	}
	
	@Override
	public synchronized boolean deleteNotice(Notice Notice){
		NoticeDAO.deleteNotice(Notice);
    	return true;
	}

	@Override
	public List<Notice> getNotice(int id, int paging) {
		return NoticeDAO.getNotice(id, paging);
	}

	@Override
	public Notice viewNotice(int id) {
		return NoticeDAO.viewNotice(id);
	}
	
}
