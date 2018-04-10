package com.polarisfinder.dreamers.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.polarisfinder.dreamers.entity.Dreamers;
import com.polarisfinder.dreamers.entity.Dreamersbookmark;
import com.polarisfinder.dreamers.entity.Dreamerscomment;
import com.polarisfinder.dreamers.entity.Dreamersfile;
import com.polarisfinder.dreamers.entity.Dreamerslike;
import com.polarisfinder.dreamers.service.DreamersService;
import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.service.UserService;


@Controller
@RequestMapping("dreamers")
public class DreamersController {
	@Autowired
	private DreamersService dreamersService;

	@Autowired
	private UserService userService;
	
	@Value("${polarisfinder.file.upload.dir}")
	private String polarisfinder_FILE_UPLOAD_DIR;
	
    
	@PostMapping("DreamersUpload")
	public ResponseEntity<JSONObject> DreamersUpload(
			@RequestParam("file") MultipartFile uploadfiles
	    ) throws Exception {
		System.out.println("Multiple file upload!");
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
        // Get file name
        String uploadedFileName="";
        uploadedFileName = uploadfiles.getOriginalFilename();
        System.out.println(uploadedFileName);

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        
        String abspath = "";
        String uploadpath = "/files/" + currentUser.getUser_id() + "/";
        try {
            abspath = saveUploadedFiles(uploadpath, uploadfiles);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String strjson = ""+
        		"{ " + 
        		"  \"files\":" + 
        		"    [" + 
        		"      {" + 
        		"        \"url\": \""+abspath+"\"" + 
        		"      }" + 
        		"    ]" + 
        		"}";
        System.out.println(strjson);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(strjson);
        
        return new ResponseEntity(json, HttpStatus.OK);
	}
	
	@PostMapping("DreamersUploadList")
	public ResponseEntity<JSONObject> DreamersUploadList(
			@RequestParam("files[]") List<MultipartFile> uploadfiles
	    ) throws Exception {
		System.out.println("Multiple file upload!");
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
        // Get file name
        String uploadedFileName="";
        for(int i=0; i<uploadfiles.size(); i++) {
        	uploadedFileName = uploadfiles.get(i).getOriginalFilename();
        	System.out.println(uploadedFileName);
        }
        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        
        String abspath = "";
        String uploadpath = "/files/" + currentUser.getUser_id() + "/";
        try {
            abspath = saveUploadedFiles(uploadpath, uploadfiles);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String strjson = ""+
        		"{ " + 
        		"  \"files\":" + 
        		"    [" + 
        		"      {" + 
        		"        \"url\": \""+abspath+"\"" + 
        		"      }" + 
        		"    ]" + 
        		"}";
        System.out.println(strjson);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(strjson);
        
        return new ResponseEntity(json, HttpStatus.OK);
	}

    //save file
    private String saveUploadedFiles(String uploadpath, List<MultipartFile> files) throws IOException {
    	String abspath = "";
    	for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next pls
            }
            byte[] bytes = file.getBytes();
            String strpath = polarisfinder_FILE_UPLOAD_DIR +uploadpath+ file.getOriginalFilename();
            Path path = Paths.get(strpath);
            abspath = uploadpath+ file.getOriginalFilename();
            System.out.println("Upload Path : "+abspath);
            File folder = new File(strpath);
            folder.getParentFile().mkdirs();
            Files.write(path, bytes);
        }
        return abspath;
    }
  //save file
    private String saveUploadedFiles(String uploadpath, MultipartFile file) throws IOException {
    	String abspath = "";
        byte[] bytes = file.getBytes();
        String strpath = polarisfinder_FILE_UPLOAD_DIR +uploadpath+ file.getOriginalFilename();
        Path path = Paths.get(strpath);
        abspath = uploadpath+ file.getOriginalFilename();
        System.out.println("Upload Path : "+abspath);
        File folder = new File(strpath);
        folder.getParentFile().mkdirs();
        Files.write(path, bytes);

        return abspath;
    }
    /*
	{"files": [
	  {
	    "name": "picture1.jpg",
	    "size": 902604,
	    "url": "http:\/\/example.org\/files\/picture1.jpg",
	    "thumbnailUrl": "http:\/\/example.org\/files\/thumbnail\/picture1.jpg",
	    "deleteUrl": "http:\/\/example.org\/files\/picture1.jpg",
	    "deleteType": "DELETE"
	  },
	  {
	    "name": "picture2.jpg",
	    "size": 841946,
	    "url": "http:\/\/example.org\/files\/picture2.jpg",
	    "thumbnailUrl": "http:\/\/example.org\/files\/thumbnail\/picture2.jpg",
	    "deleteUrl": "http:\/\/example.org\/files\/picture2.jpg",
	    "deleteType": "DELETE"
	  }
	]}
	*/
    @GetMapping("DreamersDelContent")
	public ResponseEntity<Void> DreamersDelContent(
			@RequestParam(value="id", required = false)int id
			) throws Exception {
		
    	Dreamers dreamers = new Dreamers();
		dreamers.setId(id);
		
		Dreamerscomment dreamerscomment = new Dreamerscomment();
		dreamerscomment.setDreamers_id(id);
		Dreamerslike dreamerslike = new Dreamerslike();
		dreamerslike.setDreamers_id(id);

		boolean flag = dreamersService.deleteDreamers(dreamers);
		flag = dreamersService.deleteDreamerscomment(dreamerscomment);
		flag = dreamersService.deleteDreamerslike(dreamerslike);
        if (flag) {
    		return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
	}

	@PostMapping("DreamersAddContent")
	public ResponseEntity<Void> DreamersAddContent(
			@RequestParam(value="id", required = false)int id, 
			@RequestParam(value="content", required = false)String content
			) throws Exception {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Dreamers dreamers = new Dreamers();
		dreamers.setContent(content);
		dreamers.setId(id);
		dreamers.setUser_id(currentUser.getUser_id());
		dreamers.setReg_dt(new Date());
		boolean flag = dreamersService.createDreamers(dreamers);
        if(flag) {
    		return new ResponseEntity<Void>(HttpStatus.CREATED);
        }else{
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);

        }
	}
	
	
	@GetMapping("DreamerscommentList")
	public ResponseEntity<List<Dreamerscomment>> DreamerscommentList(
			Principal pr,
			@RequestParam(value="dreamers_id", required = false)int dreamers_id,
			@RequestParam(value="paging", required = false)int paging
			) {
		System.out.println("Paging : " + paging);
		List<Dreamerscomment> list = dreamersService.getDreamerscommentById(dreamers_id, paging);
		
		return new ResponseEntity<List<Dreamerscomment>>(list, HttpStatus.OK);
	}
	
	@GetMapping("DreamersList")
	public ResponseEntity<List<Dreamers>> DreamersList(
			@RequestParam(value="id", required = false)int id,
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = null;
		try{
			currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.out.println("DreamersList : "+currentUser.getUsername());
		} catch(Exception e){
			currentUser = null;
			System.out.println("null");
		}
		
		//System.out.println("DreamersList : "+currentUser.getUsername());
		System.out.println("Paging : " + paging);
		List<Dreamers> list = dreamersService.getDreamersById(id, paging);
		for(int idx=0; idx < list.size(); idx++){
			List<Dreamerscomment> dr = dreamersService.getDreamerscommentById(list.get(idx).getId(), 0);
			list.get(idx).setDreamerscomment_list(dr);
			if(currentUser != null){
				Dreamerslike dreamerslike = new Dreamerslike();
				dreamerslike.setDreamers_id(list.get(idx).getId());
				dreamerslike.setUser_id(currentUser.getUser_id());
				if(dreamersService.checkDreamerslike(dreamerslike)){
					list.get(idx).setLike_checked(1);
				}else{
					list.get(idx).setLike_checked(0);
				}
				Dreamersbookmark dreamersbookmark = new Dreamersbookmark();
				dreamersbookmark.setDreamers_id(list.get(idx).getId());
				dreamersbookmark.setUser_id(currentUser.getUser_id());
				if(dreamersService.checkDreamersbookmark(dreamersbookmark)){
					list.get(idx).setBookmark_checked(1);
				}else{
					list.get(idx).setBookmark_checked(0);
				}
			}
		}
		
		return new ResponseEntity<List<Dreamers>>(list, HttpStatus.OK);
	}
	
	@GetMapping("Dreamersedit")
	public ResponseEntity<Dreamers> Dreamersedit(
			@RequestParam(value="id", required = false)int id
			) {
		int paging = 0;
		List<Dreamers> list = dreamersService.getDreamersById(id, paging);
		return new ResponseEntity<Dreamers>(list.get(0), HttpStatus.OK);
	}
	
	@GetMapping("Dreamerslike")
	public ResponseEntity<Void> Dreamerslike(
			//Principal pr,
			@RequestParam(value="dreamers_id", required = false)int dreamers_id
			) throws Exception {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//CurrentUser currentUser = (CurrentUser) pr;
		System.out.println("Dreamerslike !!!");
		System.out.println(currentUser.getUsername());
		System.out.println(currentUser.getUser_id());
		 
		Dreamerslike dreamerslike = new Dreamerslike();
		dreamerslike.setDreamers_id(dreamers_id); 
		dreamerslike.setUser_id(currentUser.getUser_id());
		dreamerslike.setReg_dt(new Date());
		
		Dreamers dreamers = new Dreamers();
		dreamers.setId(dreamers_id);
		
		boolean flag = dreamersService.createDreamerslike(dreamerslike);
		if(flag) {
			flag = dreamersService.increaseDreamerslikecnt(dreamers);
			return new ResponseEntity<Void>(HttpStatus.OK);
        } else{
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
	}
	
	@GetMapping("Dreamersdislike")
	public ResponseEntity<Void> Dreamersdislike(
			//Principal pr,
			@RequestParam(value="dreamers_id", required = false)int dreamers_id
			) throws Exception {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Dreamerslike !!!");
		 
		Dreamerslike dreamerslike = new Dreamerslike();
		dreamerslike.setDreamers_id(dreamers_id); 
		dreamerslike.setUser_id(currentUser.getUser_id());
		dreamerslike.setReg_dt(new Date());
		
		Dreamers dreamers = new Dreamers();
		dreamers.setId(dreamers_id);
		
		boolean flag = dreamersService.deleteDreamerslike(dreamerslike);
		if(flag) {
			flag = dreamersService.decreaseDreamerslikecnt(dreamers);
			return new ResponseEntity<Void>(HttpStatus.OK);
        } else{
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
	}
	
	@PostMapping("DreamerscommentAdd")
	public ResponseEntity<Void> DreamerscommentAdd(
			@RequestParam(value="dreamers_id", required = false)int dreamers_id, 
			@RequestParam(value="dreamers_comment", required = false)String dreamers_comment
			) throws Exception {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Dreamerscomment dreamerscomment = new Dreamerscomment();
		dreamerscomment.setDreamers_id(dreamers_id);
		dreamerscomment.setDreamers_comment(dreamers_comment);
		dreamerscomment.setUser_id(currentUser.getUser_id());
		dreamerscomment.setReg_dt(new Date());
		
		boolean flag = dreamersService.createDreamerscomment(dreamerscomment);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("Dreamersbookmark")
	public ResponseEntity<Void> Dreamersbookmark(
			//Principal pr,
			@RequestParam(value="dreamers_id", required = false)int dreamers_id
			) throws Exception {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//CurrentUser currentUser = (CurrentUser) pr;
		System.out.println("Dreamersbookmark !!!");
		System.out.println(currentUser.getUsername());
		System.out.println(currentUser.getUser_id());
		 
		Dreamersbookmark dreamersbookmark = new Dreamersbookmark();
		dreamersbookmark.setDreamers_id(dreamers_id); 
		dreamersbookmark.setUser_id(currentUser.getUser_id());
		dreamersbookmark.setReg_dt(new Date());
		
		Dreamers dreamers = new Dreamers();
		dreamers.setId(dreamers_id);
		
		boolean flag = dreamersService.createDreamersbookmark(dreamersbookmark);
		if(flag) {
			flag = dreamersService.increaseDreamersbookmarkcnt(dreamers);
			return new ResponseEntity<Void>(HttpStatus.OK);
        } else{
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
	}
	
	@GetMapping("Dreamersdisbookmark")
	public ResponseEntity<Void> Dreamersdisbookmark(
			//Principal pr,
			@RequestParam(value="dreamers_id", required = false)int dreamers_id
			) throws Exception {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Dreamersbookmark !!!");
		 
		Dreamersbookmark dreamersbookmark = new Dreamersbookmark();
		dreamersbookmark.setDreamers_id(dreamers_id); 
		dreamersbookmark.setUser_id(currentUser.getUser_id());
		dreamersbookmark.setReg_dt(new Date());
		
		Dreamers dreamers = new Dreamers();
		dreamers.setId(dreamers_id);
		
		boolean flag = dreamersService.deleteDreamersbookmark(dreamersbookmark);
		if(flag) {
			flag = dreamersService.decreaseDreamersbookmarkcnt(dreamers);
			return new ResponseEntity<Void>(HttpStatus.OK);
        } else{
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
	}
}
