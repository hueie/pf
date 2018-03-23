package com.polarisfinder.chitchatpub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.chitchatpub.dao.ChitchatpubDAO;
import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.chitchatpub.entity.Chitchatpubstar;
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
	public List<Chitchatpub> getChitchatpubByPlacelocation(Chitchatpub chitchatpub, int paging) {
		return chitchatpubDAO.getChitchatpubByPlacelocation(chitchatpub, paging);
	}
	
	@Override
	public Chitchatpubstar getChitchatpubstar(Chitchatpubstar chitchatpubstar) {
		return chitchatpubDAO.getChitchatpubstar(chitchatpubstar);
	}

	@Override
	public synchronized boolean createChitchatpubstar(Chitchatpubstar chitchatpubstar){
		chitchatpubDAO.createChitchatpubstar(chitchatpubstar);
    	return true;
	}

	@Override
	public Chitchatpub getChitchatpubById(int chitchatpub_id) {
		return chitchatpubDAO.getChitchatpubById(chitchatpub_id);
	}

	@Override
	public void increaseChitchatpubstarcnt(Chitchatpub chitchatpub) {
		return chitchatpubDAO.increaseChitchatpubstarcnt(chitchatpub);
	}
}
