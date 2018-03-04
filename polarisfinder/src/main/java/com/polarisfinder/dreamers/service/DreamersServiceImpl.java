package com.polarisfinder.dreamers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.dreamers.dao.DreamersDAO;
import com.polarisfinder.dreamers.entity.Dreamers;
import com.polarisfinder.dreamers.entity.Dreamerscomment;
import com.polarisfinder.dreamers.entity.Dreamerslike;
@Service
public class DreamersServiceImpl implements DreamersService {

	@Autowired
	private DreamersDAO DreamersDAO;
	
	@Override
	public synchronized boolean createDreamers(Dreamers Dreamers){
		DreamersDAO.createDreamers(Dreamers);
    	return true;
	}

	@Override
	public List<Dreamers> getDreamersById(int id, int paging) {
		return DreamersDAO.getDreamersById(id, paging);
	}
	
	@Override
	public synchronized boolean deleteDreamers(Dreamers Dreamers){
		DreamersDAO.deleteDreamers(Dreamers);
    	return true;
	}

	@Override
	public synchronized boolean increaseDreamerslikecnt(Dreamers Dreamers){
		DreamersDAO.increaseDreamerslikecnt(Dreamers);
    	return true;
	}

	@Override
	public synchronized boolean decreaseDreamerslikecnt(Dreamers Dreamers){
		DreamersDAO.decreaseDreamerslikecnt(Dreamers);
    	return true;
	}
	
	@Override
	public synchronized boolean createDreamerscomment(Dreamerscomment Dreamerscomment){
		DreamersDAO.createDreamerscomment(Dreamerscomment);
    	return true;
	}

	@Override
	public List<Dreamerscomment> getDreamerscommentById(int id, int paging) {
		return DreamersDAO.getDreamerscommentById(id, paging);
	}

	@Override
	public synchronized boolean deleteDreamerscomment(Dreamerscomment Dreamerscomment){
		DreamersDAO.deleteDreamerscomment(Dreamerscomment);
    	return true;
	}
	
	@Override
	public synchronized boolean createDreamerslike(Dreamerslike Dreamerslike){
		DreamersDAO.createDreamerslike(Dreamerslike);
    	return true;
	}

	@Override
	public List<Dreamerslike> getDreamerslikeById(int id, int paging) {
		return DreamersDAO.getDreamerslikeById(id, paging);
	}
	

	@Override
	public synchronized boolean deleteDreamerslike(Dreamerslike Dreamerslike){
		DreamersDAO.deleteDreamerslike(Dreamerslike);
    	return true;
	}
	
}
