package com.polarisfinder.bc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employee")
public class EmployeeRestController {
	@GetMapping("getemployees")
    public ResponseEntity<Void> getemployees() {
		
		return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
