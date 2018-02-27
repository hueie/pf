package com.polarisfinder.chitchatpub.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.chitchatpub.dao.ChitchatpubDAO;
import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.cubemap.dao.CubemapDAO;
import com.polarisfinder.cubemap.entity.Bookarng;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;
@Service
public class ChitchatpubServiceImpl implements ChitchatpubService {

	@Autowired
	private ChitchatpubDAO chitchatpubDAO;
	
	@Override
	public synchronized boolean createChitchatpub(Chitchatpub chitchatpub){
		chitchatpubDAO.createChitchatpub(chitchatpub);
    	return true;
	}

	@Override
	public List<Chitchatpub> getChitchatpubByPlacelocation(String placelocation, int paging) {
		return chitchatpubDAO.getChitchatpubByPlacelocation(placelocation, paging);
	}
}
