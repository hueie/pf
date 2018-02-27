package com.polarisfinder.user.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.polarisfinder.user.entity.Role;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.RoleService;
import com.polarisfinder.user.service.UserService;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;
import com.polarisfinder.cubemap.service.CubemapService;
import com.polarisfinder.fileupload.entity.Fileupload;
import com.polarisfinder.fileupload.service.FileuploadService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("Signin")
	public ResponseEntity<Void> Signin(
			//@RequestParam(value = "email", required = false) String email,
			//@RequestParam(value = "password", required = false) String password
	) {
		//System.out.println("Signin : "+email+","+password);
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
	public ResponseEntity<String> Signcheck(Principal pr
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
		return new ResponseEntity<String>(userEmail, HttpStatus.OK);
	}
	
	@GetMapping("SigninSuccess")
	public ResponseEntity<String> SigninSuccess(Principal pr) {
		System.out.println("SigninSuccess");
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//User user = userService.findUserByEmail(auth.getName());
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return new ResponseEntity<String>(pr.getName(),HttpStatus.OK);
	}
	
	@GetMapping("SigninFailure")
	public ResponseEntity<String> SigninFailure() {
		System.out.println("SigninFailure");
		return new ResponseEntity<String>("Sing In Failure!!",HttpStatus.OK);
	}
	
	@PostMapping("Signup")
	public ResponseEntity<String> createUser(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "last_name", required = false) String last_name,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password
	) {
		User user = new User();
		user.setActive(1);
		user.setEmail(email);
		user.setLast_name(last_name);
		user.setName(name);
		// user.setPassword(password);
		user.setPassword(bCryptPasswordEncoder.encode(password));

		Role userRole = roleService.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		boolean flag = userService.createUser(user);
		if (flag == false) {
			return new ResponseEntity<String>("Sing Up Fail!!", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Sing Up Success!!",HttpStatus.OK);
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
	*/
	
	
	@GetMapping("findByEmail")
	public ResponseEntity<User> findByEmail(@RequestParam(value = "email", required = false) String email) {
		User list = userService.findUserByEmail(email);
		return new ResponseEntity<User>(list, HttpStatus.OK);
	}

}
