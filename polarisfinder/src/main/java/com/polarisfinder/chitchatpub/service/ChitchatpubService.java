package com.polarisfinder.chitchatpub.service;

import java.util.List;

import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.cubemap.entity.Bookarng;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

public interface ChitchatpubService {
	boolean createChitchatpub(Chitchatpub chitchatpub);
	List<Chitchatpub> getChitchatpubByPlacelocation(String placelocation, int paging);
}
