package com.polarisfinder.notice.dao;

import java.util.List;

import com.polarisfinder.notice.entity.Notice;

public interface NoticeDAO {

    void createNotice(Notice Notice);
	List<Notice> getNoticeById(int id, int paging);
	void deleteNotice(Notice Notice);
	List<Notice> getNotice(int id, int paging);
	Notice viewNotice(int id);
	
	
}
