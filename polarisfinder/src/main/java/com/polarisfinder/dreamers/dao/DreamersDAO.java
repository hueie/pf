package com.polarisfinder.dreamers.dao;

import java.util.List;

import com.polarisfinder.dreamers.entity.Dreamers;
import com.polarisfinder.dreamers.entity.Dreamersbookmark;
import com.polarisfinder.dreamers.entity.Dreamerscomment;
import com.polarisfinder.dreamers.entity.Dreamerslike;

public interface DreamersDAO {

    void createDreamers(Dreamers Dreamers);
	List<Dreamers> getDreamersById(int id, int paging);
	void deleteDreamers(Dreamers Dreamers);
	
	
	void createDreamerscomment(Dreamerscomment Dreamerscomment);
	List<Dreamerscomment> getDreamerscommentById(int id, int paging);
	void deleteDreamerscomment(Dreamerscomment Dreamerscomment);
	
	void createDreamerslike(Dreamerslike Dreamerslike);
	List<Dreamerslike> getDreamerslikeById(int id, int paging);
	boolean checkDreamerslike(Dreamerslike dreamerslike);
	void deleteDreamerslike(Dreamerslike Dreamerslike);
	void increaseDreamerslikecnt(Dreamers Dreamers);
	void decreaseDreamerslikecnt(Dreamers Dreamers);
	
	void createDreamersbookmark(Dreamersbookmark Dreamersbookmark);
	List<Dreamersbookmark> getDreamersbookmarkById(int id, int paging);
	boolean checkDreamersbookmark(Dreamersbookmark dreamersbookmark);
	void deleteDreamersbookmark(Dreamersbookmark Dreamersbookmark);
	void increaseDreamersbookmarkcnt(Dreamers Dreamers);
	void decreaseDreamersbookmarkcnt(Dreamers Dreamers);
	
}
