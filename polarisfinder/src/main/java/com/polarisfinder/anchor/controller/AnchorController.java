package com.polarisfinder.anchor.controller;

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

import com.polarisfinder.anchor.entity.Anchor;
import com.polarisfinder.anchor.service.AnchorService;
import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.UserService;

@Controller
@RequestMapping("Anchor")
public class AnchorController {
	@Autowired
	private AnchorService AnchorService;

	@Autowired
	private UserService userService;
	
	@PostMapping("createAnchor")
	public ResponseEntity<Void> createAnchor(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content
			){
		
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Anchor Anchor = new Anchor();

		Anchor.setUser_id(currentUser.getUser_id());
		Anchor.setReg_dt(new Date());

		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("updateAnchor")
	public ResponseEntity<Void> updateAnchor(
			@RequestParam(value="id", required = false)int id,
			@RequestParam(value="anchor", required = false)boolean anchor
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(" star " + anchor);
		AnchorService.updateAnchor(id, anchor);
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	

	
}
