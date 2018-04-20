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
import com.polarisfinder.dreamers.entity.Dreamersbookmark;
import com.polarisfinder.dreamers.entity.Dreamerscomment;
import com.polarisfinder.dreamers.entity.Dreamerslike;
import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.UserService;

@Controller
@RequestMapping("chitchatpub")
public class ChitchatpubController {
	@Autowired
	private ChitchatpubService chitchatpubService;

	@Autowired
	private UserService userService;
	
	@PostMapping("ChitchatpubAddComment")
	public ResponseEntity<Void> CubemapAddStack(
			@RequestParam(value="placename", required = false)String placename, 
			@RequestParam(value="placelatitude", required = false)String placelatitude, 
			@RequestParam(value="placelongitude", required = false)String placelongitude, 
			@RequestParam(value="placecomment", required = false)String placecomment
			) throws Exception {
		
		CurrentUser	currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("ChitchatpubAddComment");
		
		Chitchatpub chitchatpub = new Chitchatpub();
		chitchatpub.setPlacecomment(placecomment);
		chitchatpub.setPlacelatitude(Float.valueOf(placelatitude));
		chitchatpub.setPlacelongitude(Float.valueOf(placelongitude));
		chitchatpub.setPlacename(placename);
		chitchatpub.setUser_id(currentUser.getUser_id());
		chitchatpub.setReg_dt(new Date());
		
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
		CurrentUser currentUser = null;
		try{
			currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e){
			currentUser = null;
		}
		
		Chitchatpub chitchatpub = new Chitchatpub();
		chitchatpub.setPlacelatitude( Float.valueOf(placelatitude));
		chitchatpub.setPlacelongitude( Float.valueOf(placelongitude));
		List<Chitchatpub> list = chitchatpubService.getChitchatpubByPlacelocation(chitchatpub, paging);
		
		for(int idx=0; idx < list.size(); idx++){
			User user = userService.findById( list.get(idx).getUser_id());
			User tmpuser = new User();
			tmpuser.setUsername(user.getUsername());
			tmpuser.setNickname(user.getNickname());
			tmpuser.setUser_id(user.getUser_id());
			list.get(idx).setUser(tmpuser);
			
			if(currentUser != null){
				Chitchatpubstar chitchatpubstar = new Chitchatpubstar();
				chitchatpubstar.setChitchatpub_id(list.get(idx).getId());
				chitchatpubstar.setUser_id(currentUser.getUser_id());
				chitchatpubstar = chitchatpubService.getChitchatpubstar(chitchatpubstar);
				if(chitchatpubstar == null){
					list.get(idx).setStar_cnt(0);
				}else{
					list.get(idx).setStar_cnt(chitchatpubstar.getStar_cnt());
				}
			}
		}
		
		
		
		return new ResponseEntity<List<Chitchatpub>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getMarkers")
	public ResponseEntity<List<Chitchatpub>> getMarkers(
			) {
		CurrentUser currentUser = null;
		try{
			currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e){
			currentUser = null;
		}
		
		Chitchatpub chitchatpub = new Chitchatpub();
		List<Chitchatpub> list = chitchatpubService.getMarkers(chitchatpub);
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
		
		Chitchatpubstar chitchatpubstar2 = chitchatpubService.getChitchatpubstar(chitchatpubstar);
		int preStar_cnt = 0;
		if(chitchatpubstar2 != null){
			preStar_cnt = chitchatpubstar2.getStar_cnt();
			System.out.println("pre " + preStar_cnt);
			chitchatpubstar.setId(chitchatpubstar2.getId());
			boolean flag = chitchatpubService.createChitchatpubstar(chitchatpubstar);
		} else{
			chitchatpubstar.setId(0);
			boolean flag = chitchatpubService.createChitchatpubstar(chitchatpubstar);
		}
		System.out.println("cur :" + star_cnt);
		int plusstar = star_cnt - preStar_cnt;
		System.out.println("plusstar :" + plusstar);
		
		Chitchatpub chitchatpub = new Chitchatpub();
		chitchatpub.setId(chitchatpub_id);
		chitchatpub.setStar_tot_cnt(plusstar);
		chitchatpubService.increaseChitchatpubstartotcnt(chitchatpub);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
}
