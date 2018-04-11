package com.polarisfinder.dreamers.service;

import java.util.List;

import com.polarisfinder.dreamers.entity.Dreamers;
import com.polarisfinder.dreamers.entity.Dreamersbookmark;
import com.polarisfinder.dreamers.entity.Dreamerscomment;
import com.polarisfinder.dreamers.entity.Dreamersfile;
import com.polarisfinder.dreamers.entity.Dreamerslike;

public interface DreamersService {
	boolean createDreamers(Dreamers Dreamers);
	List<Dreamers> getDreamersById(int id, int paging);
	List<Dreamers> getDreamersByObj(Dreamers Dreamers, int paging);
	boolean deleteDreamers(Dreamers Dreamers);
	
	boolean createDreamerscomment(Dreamerscomment Dreamerscomment);
	List<Dreamerscomment> getDreamerscommentById(int id, int paging);
	boolean deleteDreamerscomment(Dreamerscomment Dreamerscomment);
	
	
	boolean createDreamerslike(Dreamerslike Dreamerslike);
	List<Dreamerslike> getDreamerslikeById(int id, int paging);
	boolean checkDreamerslike(Dreamerslike dreamerslike);
	boolean deleteDreamerslike(Dreamerslike Dreamerslike);
	boolean increaseDreamerslikecnt(Dreamers Dreamers);
	boolean decreaseDreamerslikecnt(Dreamers Dreamers);
	

	boolean createDreamersbookmark(Dreamersbookmark dreamersbookmark);
	List<Dreamersbookmark> getDreamersbookmarkById(int id, int paging);
	boolean checkDreamersbookmark(Dreamersbookmark dreamersbookmark);
	boolean deleteDreamersbookmark(Dreamersbookmark dreamersbookmark);
	boolean increaseDreamersbookmarkcnt(Dreamers dreamers);
	boolean decreaseDreamersbookmarkcnt(Dreamers dreamers);
	

	boolean createDreamersfile(Dreamersfile Dreamersfile);
	List<Dreamersfile> getDreamersfileById(int id, int paging);
	boolean deleteDreamersfile(Dreamersfile Dreamersfile);
}
