package com.polarisfinder.chitchatpub.controller;

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

import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.chitchatpub.entity.Chitchatpubstar;
import com.polarisfinder.chitchatpub.service.ChitchatpubService;
import com.polarisfinder.dreamers.entity.Dreamers;
import com.polarisfinder.dreamers.entity.Dreamerslike;
import com.polarisfinder.user.entity.CurrentUser;

@Controller
@RequestMapping("chitchatpub")
public class ChitchatpubController {
	@Autowired
	private ChitchatpubService chitchatpubService;
	
	@PostMapping("ChitchatpubAddComment")
	public ResponseEntity<Void> CubemapAddStack(
			@RequestParam(value="placename", required = false)String placename, 
			@RequestParam(value="placelatitude", required = false)String placelatitude, 
			@RequestParam(value="placelongitude", required = false)String placelongitude, 
			@RequestParam(value="placecomment", required = false)String placecomment
			) throws Exception {
		
		System.out.println("ChitchatpubAddComment");
		
		Chitchatpub chitchatpub = new Chitchatpub();
		chitchatpub.setPlacecomment(placecomment);
		chitchatpub.setPlacelatitude(Float.valueOf(placelatitude));
		chitchatpub.setPlacelongitude(Float.valueOf(placelongitude));
		chitchatpub.setPlacename(placename);
		
		boolean flag = chitchatpubService.createChitchatpub(chitchatpub);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("ChitchatpubList")
	public ResponseEntity<List<Chitchatpub>> ChitchatpubList(
			@RequestParam(value="placelatitude", required = false)String placelatitude, 
			@RequestParam(value="placelongitude", required = false)String placelongitude, 
			@RequestParam(value="paging", required = false)int paging
			) {
		System.out.println("Paging : " + paging);
		System.out.println("placelatitude : "+placelatitude + " placelongitude : "+placelongitude);
		Chitchatpub chitchatpub = new Chitchatpub();
		chitchatpub.setPlacelatitude( Float.valueOf(placelatitude));
		chitchatpub.setPlacelongitude( Float.valueOf(placelongitude));
		List<Chitchatpub> list = chitchatpubService.getChitchatpubByPlacelocation(chitchatpub, paging);
		return new ResponseEntity<List<Chitchatpub>>(list, HttpStatus.OK);
	}
	@GetMapping("Chitchatpubstar")
	public ResponseEntity<Void> Chitchatpubstar(
			@RequestParam(value="id", required = false)int chitchatpub_id, 
			@RequestParam(value="starcnt", required = false)int star_cnt
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Chitchatpubstar chitchatpubstar = new Chitchatpubstar();
		chitchatpubstar.setChitchatpub_id(chitchatpub_id);
		chitchatpubstar.setStar_cnt(star_cnt);
		chitchatpubstar.setUser_id(currentUser.getUser_id());
		chitchatpubstar.setReg_dt(new Date());
		
		Chitchatpub chitchatpub = chitchatpubService.getChitchatpubById(chitchatpub_id);
		if(chitchatpub == null){
			
		} else {
			
		}
		boolean flag = chitchatpubService.createChitchatpubstar(chitchatpubstar);
		if(flag) {
			return new ResponseEntity<Void>(HttpStatus.OK);
        } else{
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		
	}
	
}
