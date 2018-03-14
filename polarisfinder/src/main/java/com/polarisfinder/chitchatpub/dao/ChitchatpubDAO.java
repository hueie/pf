package com.polarisfinder.chitchatpub.dao;

import java.util.List;

import com.polarisfinder.chitchatpub.entity.Chitchatpub;

public interface ChitchatpubDAO {

    void createChitchatpub(Chitchatpub chitchatpub);
	List<Chitchatpub> getChitchatpubByPlacelocation(Chitchatpub chitchatpub, int paging);

}
