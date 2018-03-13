package com.polarisfinder.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {
	@GetMapping("getok")
	public ResponseEntity<Void> OK() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("getconflict")
	public ResponseEntity<Void> CONFLICT() {
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
}
