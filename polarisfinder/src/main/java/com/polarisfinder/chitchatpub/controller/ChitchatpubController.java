package com.polarisfinder.chitchatpub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.chitchatpub.service.ChitchatpubService;

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
	
}
