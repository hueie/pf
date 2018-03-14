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
			@RequestParam(value="placelatitude", required = false)int placelatitude, 
			@RequestParam(value="placelongitude", required = false)int placelongitude, 
			@RequestParam(value="placecomment", required = false)String placecomment, 
			UriComponentsBuilder builder
			) throws Exception {
		
		System.out.println("ChitchatpubAddComment");
		
		Chitchatpub chitchatpub = new Chitchatpub();
		chitchatpub.setPlacecomment(placecomment);
		chitchatpub.setPlacelatitude(placelatitude);
		chitchatpub.setPlacelongitude(placelongitude);
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
	public ResponseEntity<List<Chitchatpub>> ChitchatpubList(
			@RequestParam(value="placelatitude", required = false)int placelatitude, 
			@RequestParam(value="placelongitude", required = false)int placelongitude, 
			@RequestParam(value="paging", required = false)int paging
			) {
		System.out.println("Paging : " + paging);

		Chitchatpub chitchatpub = new Chitchatpub();
		chitchatpub.setPlacelatitude(placelatitude);
		chitchatpub.setPlacelongitude(placelongitude);
		List<Chitchatpub> list = chitchatpubService.getChitchatpubByPlacelocation(chitchatpub, paging);
		return new ResponseEntity<List<Chitchatpub>>(list, HttpStatus.OK);
	}
	
}
