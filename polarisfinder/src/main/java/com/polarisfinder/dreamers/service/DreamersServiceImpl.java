package com.polarisfinder.dreamers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.dreamers.dao.DreamersDAO;
import com.polarisfinder.dreamers.entity.Dreamers;
import com.polarisfinder.dreamers.entity.Dreamersbookmark;
import com.polarisfinder.dreamers.entity.Dreamerscomment;
import com.polarisfinder.dreamers.entity.Dreamersfile;
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
	public boolean checkDreamerslike(Dreamerslike dreamerslike) {
		return DreamersDAO.checkDreamerslike(dreamerslike);
	}
	
	@Override
	public synchronized boolean deleteDreamerslike(Dreamerslike Dreamerslike){
		DreamersDAO.deleteDreamerslike(Dreamerslike);
    	return true;
	}
	
	
	@Override
	public synchronized boolean createDreamersbookmark(Dreamersbookmark Dreamersbookmark){
		DreamersDAO.createDreamersbookmark(Dreamersbookmark);
    	return true;
	}

	@Override
	public List<Dreamersbookmark> getDreamersbookmarkById(int id, int paging) {
		return DreamersDAO.getDreamersbookmarkById(id, paging);
	}
	

	@Override
	public boolean checkDreamersbookmark(Dreamersbookmark dreamersbookmark) {
		return DreamersDAO.checkDreamersbookmark(dreamersbookmark);
	}
	
	@Override
	public synchronized boolean deleteDreamersbookmark(Dreamersbookmark Dreamersbookmark){
		DreamersDAO.deleteDreamersbookmark(Dreamersbookmark);
    	return true;
	}
	
	@Override
	public synchronized boolean increaseDreamersbookmarkcnt(Dreamers Dreamers){
		DreamersDAO.increaseDreamersbookmarkcnt(Dreamers);
    	return true;
	}

	@Override
	public synchronized boolean decreaseDreamersbookmarkcnt(Dreamers Dreamers){
		DreamersDAO.decreaseDreamersbookmarkcnt(Dreamers);
    	return true;
	}
	
	@Override
	public synchronized boolean createDreamersfile(Dreamersfile Dreamersfile){
		DreamersDAO.createDreamersfile(Dreamersfile);
    	return true;
	}

	@Override
	public List<Dreamersfile> getDreamersfileById(int id, int paging) {
		return DreamersDAO.getDreamersfileById(id, paging);
	}
	
	@Override
	public synchronized boolean deleteDreamersfile(Dreamersfile Dreamersfile){
		DreamersDAO.deleteDreamersfile(Dreamersfile);
    	return true;
	}
}
