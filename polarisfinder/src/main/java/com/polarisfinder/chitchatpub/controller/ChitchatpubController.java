package com.polarisfinder.chitchatpub.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.atestpage.service.IArticleService;
import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.chitchatpub.service.ChitchatpubService;
import com.polarisfinder.cubemap.entity.Bookarng;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;
import com.polarisfinder.cubemap.service.CubemapService;

@Controller
@RequestMapping("chitchatpub")
public class ChitchatpubController {
	@Autowired
	private ChitchatpubService chitchatpubService;
	
	@PostMapping("ChitchatpubAddComment")
	public ResponseEntity<Void> CubemapAddStack(
			@RequestParam(value="placename", required = false)String placename, 
			@RequestParam(value="placelocation", required = false)String placelocation, 
			@RequestParam(value="placecomment", required = false)String placecomment, 
			UriComponentsBuilder builder
			) throws Exception {
		
		System.out.println("ChitchatpubAddComment");
		
		Chitchatpub chitchatpub = new Chitchatpub();
		chitchatpub.setPlacecomment(placecomment);
		chitchatpub.setPlacelocation(placelocation);
		chitchatpub.setPlacename(placename);
		
		boolean flag = chitchatpubService.createChitchatpub(chitchatpub);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        /*
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/chitchatpub/Cubemap?stack_id={stack_id}").buildAndExpand(stack.getStack_id()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		*/
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("ChitchatpubList")
	public ResponseEntity<List<Chitchatpub>> ChitchatpubList(@RequestParam(
			value="placelocation", required = false)String placelocation,
			@RequestParam(value="paging", required = false)int paging
			) {
		System.out.println("Paging : " + paging);
		List<Chitchatpub> list = chitchatpubService.getChitchatpubByPlacelocation(placelocation, paging);
		return new ResponseEntity<List<Chitchatpub>>(list, HttpStatus.OK);
	}
	
}
