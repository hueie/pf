package com.polarisfinder.anchor.service;

import java.util.List;

import com.polarisfinder.anchor.entity.Anchor;

public interface AnchorService {
	boolean createAnchor(Anchor Anchor);
	List<Anchor> getAnchorById(int id, int paging);
	boolean deleteAnchor(Anchor Anchor);
	
	List<Anchor> getAnchor(int id, int paging);
	void updateAnchor(int id, boolean anchor);
	Anchor viewAnchor(int id) ;
}
