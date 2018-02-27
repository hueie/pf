package com.polarisfinder.cammapping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cammapping.dao.CammappingDAO;
import com.polarisfinder.cammapping.entity.Cam;
import com.polarisfinder.cammapping.entity.Cammapping;
import com.polarisfinder.cubemap.dao.CubemapDAO;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

@Service
public class CammappingServiceImpl implements CammappingService {
	@Autowired
	private CammappingDAO cammappingDAO;
	
	@Override
	public synchronized boolean createCammapping(Cammapping cammapping){
		cammappingDAO.createCammapping(cammapping);
		return true;
	}
	@Override
	public boolean deleteCammappingByFileuploadId(int fileupload_id, int user_id) {
		cammappingDAO.deleteCammappingByFileuploadId(fileupload_id, user_id);
		return true;
	}
	
	@Override
	public void updateBooksfIdToCammapping(Cammapping cammapping) {
		cammappingDAO.updateBooksfIdToCammapping(cammapping);
	}
	@Override
	public List<Cam> getAllCams() {
		return cammappingDAO.getAllCams();
	}
	
	@Override
	public List<Cammapping> getLinesfsByFileuploadId(int fileupload_id, int user_id) {
		return cammappingDAO.getLinesfsByFileuploadId(fileupload_id, user_id);
	}
}
