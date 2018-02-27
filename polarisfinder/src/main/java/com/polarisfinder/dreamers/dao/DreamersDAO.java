package com.polarisfinder.dreamers.dao;

import java.util.List;

import com.polarisfinder.dreamers.entity.Dreamers;

public interface DreamersDAO {

    void createDreamers(Dreamers Dreamers);
	List<Dreamers> getDreamersById(int id, int paging);

}
