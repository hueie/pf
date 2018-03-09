package com.polarisfinder.main.controller;

import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.polarisfinder.cammapping.entity.Cammapping;

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
