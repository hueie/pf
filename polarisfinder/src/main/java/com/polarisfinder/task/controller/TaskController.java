package com.polarisfinder.task.controller;

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

import com.polarisfinder.task.entity.Task;
import com.polarisfinder.task.service.TaskService;
import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.UserService;

@Controller
@RequestMapping("task")
public class TaskController {
	@Autowired
	private TaskService TaskService;

	@Autowired
	private UserService userService;
	
	@PostMapping("setTask")
	public ResponseEntity<Void> setTask(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content
			){
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Task Task = new Task();
		Task.setSubject(subject);
		Task.setContent(content);
		Task.setStatus(0);
		Task.setUser_id(currentUser.getUser_id());
		Task.setReg_dt(new Date());
		TaskService.createTask(Task);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("getTask")
	public ResponseEntity<List<Task>> getTask(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Task> list = TaskService.getTask(currentUser.getUser_id(), paging);
		return new ResponseEntity<List<Task>>(list, HttpStatus.OK);
	}
	
}
