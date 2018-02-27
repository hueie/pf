package com.polarisfinder.dreamers.service;

import java.util.List;

import com.polarisfinder.dreamers.entity.Dreamers;

public interface DreamersService {
	boolean createDreamers(Dreamers Dreamers);
	List<Dreamers> getDreamersById(int id, int paging);
}
