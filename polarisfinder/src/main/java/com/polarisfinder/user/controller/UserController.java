package com.polarisfinder.user.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.entity.Role;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.RoleService;
import com.polarisfinder.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("login")
	public ResponseEntity<CurrentUser> login() {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("hi! : "+currentUser.getUsername());
		return new ResponseEntity<CurrentUser>(currentUser, HttpStatus.OK);
	}
	
	@PostMapping("Signup")
	public ResponseEntity<Void> createUser(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "password", required = false) String password
	) {
		System.out.println("Sign Up!!!");
		User user = new User();
		user.setActive(1);
		user.setUsername(username);
		user.setNickname(nickname);
		// user.setPassword(password);
		user.setPassword(bCryptPasswordEncoder.encode(password));

		Role userRole = roleService.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		boolean flag = userService.createUser(user);
		if (flag) {
			System.out.println("sign up true");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else{
			System.out.println("sign up false");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("findByUserName")
	public ResponseEntity<User> findByUserName(@RequestParam(value = "username", required = false) String username) {
		System.out.println("findByUserName");
		User user = userService.findByUserName(username);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	/*
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	
	
	@PostMapping("Signin")
	public ResponseEntity<Void> Signin(
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password
	) {
		System.out.println("Signin : "+email+","+password);
		//return new ResponseEntity<String>( "Sing In Success!!",HttpStatus.OK);
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@PostMapping("Signout")
	public ResponseEntity<Void> Signout(
	) {
		System.out.println("Sign out!!!");
		return new ResponseEntity<Void>( HttpStatus.OK);
	}

	@GetMapping("SignoutSuccess")
	public ResponseEntity<Void> SignoutSuccess(Principal pr) {
		System.out.println("SignoutSuccess");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("Signcheck")
	public ResponseEntity<Principal> Signcheck(Principal pr
			//@RequestParam(value = "email", required = false) String email,
			//@RequestParam(value = "password", required = false) String password
	) {
		//return new ResponseEntity<String>( "Sing In Success!!",HttpStatus.OK);
		String userEmail;
		if(pr != null) {
			userEmail = pr.getName();
		} else {
			userEmail = "";
		}
		System.out.println("Signcheck : "+userEmail);
		return new ResponseEntity<Principal>(pr, HttpStatus.OK);
	}
	
	@GetMapping("SigninSuccess")
	public ResponseEntity<Principal> SigninSuccess(Principal pr) {
		System.out.println("SigninSuccess");
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//User user = userService.findUserByEmail(auth.getName());
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return new ResponseEntity<Principal>(pr,HttpStatus.OK);
	}
	
	@GetMapping("SigninFailure")
	public ResponseEntity<String> SigninFailure() {
		System.out.println("SigninFailure");
		return new ResponseEntity<String>("Sing In Failure!!",HttpStatus.OK);
	}
	
	
	*/
	
	
	

}
