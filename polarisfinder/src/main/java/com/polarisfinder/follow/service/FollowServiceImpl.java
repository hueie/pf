package com.polarisfinder.follow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.follow.dao.FollowDAO;
import com.polarisfinder.follow.entity.Follow;
@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDAO FollowDAO;
	
	@Override
	public synchronized boolean createFollow(Follow Follow){
		FollowDAO.createFollow(Follow);
    	return true;
	}

	@Override
	public List<Follow> getFollowById(int id, int paging) {
		return FollowDAO.getFollowById(id, paging);
	}
	
	@Override
	public synchronized boolean deleteFollow(Follow Follow){
		FollowDAO.deleteFollow(Follow);
    	return true;
	}

	@Override
	public List<Follow> getFollow(int id, int paging) {
		return FollowDAO.getFollow(id, paging);
	}
	@Override
	public List<Follow> getFollowSent(int id, int paging) {
		return FollowDAO.getFollowSent(id, paging);
	}
	@Override
	public List<Follow> getFollowStarred(int id, int paging) {
		return FollowDAO.getFollowStarred(id, paging);
	}
}
