package com.polarisfinder.follow.dao;

import java.util.List;

import com.polarisfinder.follow.entity.Follow;

public interface FollowDAO {

    void createFollow(Follow Follow);
	List<Follow> getFollowById(int id, int paging);
	void deleteFollow(Follow Follow);
	

	List<Follow> getFollow(int id, int paging);
	List<Follow> getFollowSent(int id, int paging);
	List<Follow> getFollowStarred(int id, int paging);
	
	
}
