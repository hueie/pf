package com.polarisfinder;

//@Controller
//@RequestMapping("")
public class IndexController {
	//@GetMapping("")
	public String index() {
		System.out.println("Index Home!!!");
		return "index.html";
	}
	
}
