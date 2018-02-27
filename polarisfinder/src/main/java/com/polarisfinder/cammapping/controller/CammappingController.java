package com.polarisfinder.cammapping.controller;

import java.security.Principal;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.polarisfinder.cammapping.entity.Cam;
import com.polarisfinder.cammapping.entity.Cammapping;
import com.polarisfinder.cammapping.service.CammappingService;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;
import com.polarisfinder.cubemap.service.CubemapService;
import com.polarisfinder.fileupload.entity.Fileupload;
import com.polarisfinder.fileupload.service.FileuploadService;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.UserService;

@Controller
@RequestMapping("cammapping")
public class CammappingController {
	@Autowired
	private CammappingService cammappingService;
	@Autowired
	private FileuploadService fileuploadService;

	@Autowired
	private UserService userService;
	
	@PostMapping("Cammapping")
	public ResponseEntity<Void> createMapping(Principal pr,
			@RequestParam(value="line_list", required = false)String line_list,
			@RequestParam(value="fileupload_id", required = false)int fileupload_id
			
			) throws JSONException {
		User user = userService.findUserByEmail(pr.getName());
        int userId = user.getUser_id();
        
		cammappingService.deleteCammappingByFileuploadId(fileupload_id, userId);
        
		JSONObject obj = new JSONObject(line_list);
		JSONArray items = obj.getJSONArray("line_list");

		for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            //int fileupload_id = item.getInt("fileupload_id");
            int line_id = item.getInt("line_id");
            int start_x = item.getInt("start_x");
            int end_x = item.getInt("end_x");
            int start_y = item.getInt("start_y");
            int end_y = item.getInt("end_y");
            
            Cammapping cammapping = new Cammapping();
            cammapping.setFileupload_id(fileupload_id);
            cammapping.setLine_id(line_id);
            cammapping.setStart_x(start_x);
            cammapping.setEnd_x(end_x);
            cammapping.setStart_y(start_y);
            cammapping.setEnd_y(end_y);
            cammapping.setBooksf_id(0);
            cammapping.setCammapping_user_id(userId);
            boolean flag = cammappingService.createCammapping(cammapping);
            if (flag == false) {
            	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
            }
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("getAllCams")
	public ResponseEntity<List<Fileupload>> getAllCams(Principal pr) {
		User user = userService.findUserByEmail(pr.getName());
        int userId = user.getUser_id();
		//List<Cam> list = cammappingService.getAllCams();
		List<Fileupload> list = fileuploadService.getFilesByFileuploadRegId(userId);
		return new ResponseEntity<List<Fileupload>>(list, HttpStatus.OK);
	}
	
	
	
	@GetMapping("getLinesfsByFileuploadId")
	public ResponseEntity<List<Cammapping>> getLinesfsByFileuploadId(
			Principal pr, 
			@RequestParam(value="fileupload_id", required = false)int fileupload_id
			) {
		User user = userService.findUserByEmail(pr.getName());
        int userId = user.getUser_id();
		List<Cammapping> list = cammappingService.getLinesfsByFileuploadId(fileupload_id, userId);
		if(list == null || list.isEmpty()) {
			
		}
		return new ResponseEntity<List<Cammapping>>(list, HttpStatus.OK);
	}
	
	@PutMapping("updateBooksfIdToCammapping")
	public ResponseEntity<Void> updateBooksfIdToCammapping(Principal pr, @RequestParam(value="booksf_id", required = false)int booksf_id,
			@RequestParam(value="cammapping_id", required = false)int cammapping_id) {
		User user = userService.findUserByEmail(pr.getName());
        int userId = user.getUser_id();
        
		Cammapping cammapping = new Cammapping();
        cammapping.setCammapping_id(cammapping_id);
        cammapping.setBooksf_id(booksf_id);
		cammappingService.updateBooksfIdToCammapping(cammapping);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
