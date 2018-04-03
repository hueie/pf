package com.polarisfinder.anchor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.anchor.dao.AnchorDAO;
import com.polarisfinder.anchor.entity.Anchor;
@Service
public class AnchorServiceImpl implements AnchorService {

	@Autowired
	private AnchorDAO AnchorDAO;
	
	@Override
	public synchronized boolean createAnchor(Anchor Anchor){
		AnchorDAO.createAnchor(Anchor);
    	return true;
	}

	@Override
	public List<Anchor> getAnchorById(int id, int paging) {
		return AnchorDAO.getAnchorById(id, paging);
	}
	
	@Override
	public synchronized boolean deleteAnchor(Anchor Anchor){
		AnchorDAO.deleteAnchor(Anchor);
    	return true;
	}

	@Override
	public List<Anchor> getAnchor(int id, int paging) {
		return AnchorDAO.getAnchor(id, paging);
	}
	@Override
	public void updateAnchor(int id, boolean anchor) {
		AnchorDAO.updateAnchor(id, anchor);
	}
	@Override
	public Anchor viewAnchor(int id) {
		return AnchorDAO.viewAnchor(id);
	}
	
	
}
