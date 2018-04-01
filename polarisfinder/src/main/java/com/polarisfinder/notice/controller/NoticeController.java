package com.polarisfinder.notice.controller;

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

import com.polarisfinder.notice.entity.Notice;
import com.polarisfinder.notice.service.NoticeService;
import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.service.UserService;

@Controller
@RequestMapping("Notice")
public class NoticeController {
	@Autowired
	private NoticeService NoticeService;

	@Autowired
	private UserService userService;
	
	@PostMapping("setNotice")
	public ResponseEntity<Void> setNotice(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content
			){
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Notice Notice = new Notice();
		Notice.setSubject(subject);
		Notice.setContent(content);
		Notice.setUser_id(currentUser.getUser_id());
		Notice.setReg_dt(new Date());
		NoticeService.createNotice(Notice);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("getNotice")
	public ResponseEntity<List<Notice>> getNotice(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Notice> list = NoticeService.getNotice(currentUser.getUser_id(), paging);
		return new ResponseEntity<List<Notice>>(list, HttpStatus.OK);
	}
	
}
