package com.polarisfinder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.polarisfinder.atestpage.entity.Article;

//@Controller
//@RequestMapping("")
public class IndexController {
	//@GetMapping("")
	public String index() {
		System.out.println("Index Home!!!");
		return "index.html";
	}
	
}
