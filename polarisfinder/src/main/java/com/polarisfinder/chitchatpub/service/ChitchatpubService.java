package com.polarisfinder.chitchatpub.service;

import java.util.List;

import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.chitchatpub.entity.Chitchatpubstar;

public interface ChitchatpubService {
	boolean createChitchatpub(Chitchatpub chitchatpub);
	List<Chitchatpub> getChitchatpubByPlacelocation(Chitchatpub chitchatpub, int paging);
	List<Chitchatpub> getMarkers(Chitchatpub chitchatpub);
    Chitchatpub getChitchatpubById(int chitchatpub_id); 
	Chitchatpubstar getChitchatpubstar(Chitchatpubstar chitchatpubstar); 
	boolean createChitchatpubstar(Chitchatpubstar chitchatpubstar);
	void increaseChitchatpubstartotcnt(Chitchatpub chitchatpub);
}
