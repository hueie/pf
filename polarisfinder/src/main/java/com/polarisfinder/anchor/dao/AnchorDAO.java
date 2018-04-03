package com.polarisfinder.anchor.dao;

import java.util.List;

import com.polarisfinder.anchor.entity.Anchor;

public interface AnchorDAO {

    void createAnchor(Anchor Anchor);
	List<Anchor> getAnchorById(int id, int paging);
	void deleteAnchor(Anchor Anchor);
	List<Anchor> getAnchor(int id, int paging);
	void updateAnchor(int id, boolean anchor);
	Anchor viewAnchor(int id);
}
