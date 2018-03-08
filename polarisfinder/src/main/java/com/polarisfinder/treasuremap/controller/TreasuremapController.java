package com.polarisfinder.treasuremap.controller;

import java.util.ArrayList;
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
@RequestMapping("treasuremap")
public class TreasuremapController {
	@GetMapping("country")
	public ResponseEntity<List<Country>> getAllCams() {
		List<Country> list = new ArrayList();
		Country g = new Country();
		g.setId(1);
		g.setCountry("Australia");;
		g.setCode("au");
		list.add(g);
		g = new Country();
		g.setId(2);
		g.setCountry("Canada");;
		g.setCode("ca");
		list.add(g);
		g = new Country();
		g.setId(3);
		g.setCountry("Japan");;
		g.setCode("jp");
		list.add(g);
		g = new Country();
		g.setId(4);
		g.setCountry("United State");;
		g.setCode("us");
		list.add(g);
		
		return new ResponseEntity<List<Country>>(list, HttpStatus.OK);
	}
	
	@GetMapping("au")
	public ResponseEntity<List<Topic>> au() {
		System.out.println("au");
		List<Topic> list = new ArrayList();
		Topic g = new Topic();
		g.setId(1);
		g.setName("Visa");;
		g.setCode("visa");
		list.add(g);
		g = new Topic();
		g.setId(2);
		g.setName("Life");;
		g.setCode("life");
		list.add(g);
		g = new Topic();
		g.setId(3);
		g.setName("Money");;
		g.setCode("money");
		list.add(g);
		
		return new ResponseEntity<List<Topic>>(list, HttpStatus.OK);
	}
	
	@GetMapping("ca")
	public ResponseEntity<List<Topic>> ca() {
		List<Topic> list = new ArrayList();
		Topic g = new Topic();
		g.setId(1);
		g.setName("Visa");
		g.setCode("visa");
		list.add(g);
		g = new Topic();
		g.setId(2);
		g.setName("Life");
		g.setCode("life");
		list.add(g);
		g = new Topic();
		g.setId(3);
		g.setName("Money");
		g.setCode("money");
		list.add(g);
		
		return new ResponseEntity<List<Topic>>(list, HttpStatus.OK);
	}
	
	@GetMapping("jp")
	public ResponseEntity<List<Topic>> jp() {
		List<Topic> list = new ArrayList();
		Topic g = new Topic();
		g.setId(1);
		g.setName("Visa");
		g.setCode("visa");
		list.add(g);
		g = new Topic();
		g.setId(2);
		g.setName("Life");
		g.setCode("life");
		list.add(g);
		g = new Topic();
		g.setId(3);
		g.setName("Money");
		g.setCode("money");
		list.add(g);
		
		return new ResponseEntity<List<Topic>>(list, HttpStatus.OK);
	}
	
	@GetMapping("us")
	public ResponseEntity<List<Topic>> us() {
		List<Topic> list = new ArrayList();
		Topic g = new Topic();
		g.setId(1);
		g.setName("Visa");
		g.setCode("visa");
		list.add(g);
		g = new Topic();
		g.setId(2);
		g.setName("Life");
		g.setCode("life");
		list.add(g);
		g = new Topic();
		g.setId(3);
		g.setName("Money");
		g.setCode("money");
		list.add(g);
		
		return new ResponseEntity<List<Topic>>(list, HttpStatus.OK);
	}
}
