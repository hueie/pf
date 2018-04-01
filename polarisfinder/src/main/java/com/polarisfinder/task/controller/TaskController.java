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
@RequestMapping("Task")
public class TaskController {
	@Autowired
	private TaskService TaskService;

	@Autowired
	private UserService userService;
	
	@PostMapping("add")
	public ResponseEntity<Void> add(
			@RequestParam("Tasking_user_id") String Tasking_user_id
			){
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Task Task = new Task();
		User user = userService.findByUserName(Tasking_user_id);
		Task.setTasking_user_id(user.getUser_id());
		Task.setUser_id(currentUser.getUser_id());
		Task.setReg_dt(new Date());
		TaskService.createTask(Task);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("setTasking")
	public ResponseEntity<Void> setTasking(
			@RequestParam("Tasking_user_id") int Tasking_user_id
			){
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Task Task = new Task();
		User user = userService.findById(Tasking_user_id);
		Task.setTasking_user_id(user.getUser_id());
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
	
	@GetMapping("getTasking")
	public ResponseEntity<List<Task>> getTasking(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Task> list = TaskService.getTasking(currentUser.getUser_id(), paging);
		for(int idx = 0; idx < list.size(); idx++){
			User user = userService.findById( list.get(idx).getTasking_user_id());
			User tmpuser = new User();
			tmpuser.setUsername(user.getUsername());
			tmpuser.setUser_id(user.getUser_id());
			list.get(idx).setUser(tmpuser);
		}
		return new ResponseEntity<List<Task>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getTasker")
	public ResponseEntity<List<Task>> getTasker(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Task> list = TaskService.getTasker(currentUser.getUser_id(), paging);
		return new ResponseEntity<List<Task>>(list, HttpStatus.OK);
	}
	
	
}
