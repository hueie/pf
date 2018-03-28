package com.polarisfinder.follow.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.polarisfinder.follow.entity.Follow;
import com.polarisfinder.follow.service.FollowService;
import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.UserService;

@Controller
@RequestMapping("Follow")
public class FollowController {
	@Autowired
	private FollowService FollowService;

	@Autowired
	private UserService userService;
	
	@PostMapping("add")
	public ResponseEntity<Void> add(
			@RequestParam("following_user_id") String following_user_id
			){
		
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Follow Follow = new Follow();
		User user = userService.findByUserName(following_user_id);
		Follow.setFollowing_user_id(user.getUser_id());
		Follow.setUser_id(currentUser.getUser_id());
		Follow.setReg_dt(new Date());
		FollowService.createFollow(Follow);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("getFollow")
	public ResponseEntity<List<Follow>> getFollow(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Follow> list = FollowService.getFollow(currentUser.getUser_id(), paging);
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getFollowing")
	public ResponseEntity<List<Follow>> getFollowSent(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Follow> list = FollowService.getFollowing(currentUser.getUser_id(), paging);
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getFollower")
	public ResponseEntity<List<Follow>> getFollowStarred(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Follow> list = FollowService.getFollower(currentUser.getUser_id(), paging);
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}
	
	
}
