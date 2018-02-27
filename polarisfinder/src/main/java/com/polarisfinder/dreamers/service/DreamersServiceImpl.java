package com.polarisfinder.dreamers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.dreamers.dao.DreamersDAO;
import com.polarisfinder.dreamers.entity.Dreamers;
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
}
