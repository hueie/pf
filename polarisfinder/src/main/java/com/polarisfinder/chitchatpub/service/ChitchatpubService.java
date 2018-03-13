package com.polarisfinder.chitchatpub.service;

import java.util.List;

import com.polarisfinder.chitchatpub.entity.Chitchatpub;

public interface ChitchatpubService {
	boolean createChitchatpub(Chitchatpub chitchatpub);
	List<Chitchatpub> getChitchatpubByPlacelocation(String placelocation, int paging);
}
