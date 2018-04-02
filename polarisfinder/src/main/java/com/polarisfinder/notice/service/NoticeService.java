package com.polarisfinder.notice.service;

import java.util.List;

import com.polarisfinder.notice.entity.Notice;

public interface NoticeService {
	boolean createNotice(Notice Notice);
	List<Notice> getNoticeById(int id, int paging);
	boolean deleteNotice(Notice Notice);
	List<Notice> getNotice(int id, int paging);
	Notice viewNotice(int id) ;
	
}
