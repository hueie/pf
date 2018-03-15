package com.polarisfinder.dreamers.service;

import java.util.List;

import com.polarisfinder.dreamers.entity.Dreamers;
import com.polarisfinder.dreamers.entity.Dreamerscomment;
import com.polarisfinder.dreamers.entity.Dreamerslike;

public interface DreamersService {
	boolean createDreamers(Dreamers Dreamers);
	List<Dreamers> getDreamersById(int id, int paging);
	boolean deleteDreamers(Dreamers Dreamers);
	boolean increaseDreamerslikecnt(Dreamers Dreamers);
	boolean decreaseDreamerslikecnt(Dreamers Dreamers);
	
	boolean createDreamerscomment(Dreamerscomment Dreamerscomment);
	List<Dreamerscomment> getDreamerscommentById(int id, int paging);
	boolean deleteDreamerscomment(Dreamerscomment Dreamerscomment);
	boolean createDreamerslike(Dreamerslike Dreamerslike);
	List<Dreamerslike> getDreamerslikeById(int id, int paging);
	boolean checkDreamerslike(Dreamerslike dreamerslike);
	boolean deleteDreamerslike(Dreamerslike Dreamerslike);
	
	
}
