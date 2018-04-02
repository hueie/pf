package com.polarisfinder.anchor.dao;

import java.util.List;

import com.polarisfinder.anchor.entity.Anchor;

public interface AnchorDAO {

    void createAnchor(Anchor Anchor);
	List<Anchor> getAnchorById(int id, int paging);
	void deleteAnchor(Anchor Anchor);
	List<Anchor> getAnchor(int id, int paging);
	void updateStarred(int id, boolean star);
	Anchor viewAnchor(int id);
}
