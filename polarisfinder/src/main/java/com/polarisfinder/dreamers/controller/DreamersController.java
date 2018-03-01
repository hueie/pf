package com.polarisfinder.dreamers.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.chitchatpub.service.ChitchatpubService;
import com.polarisfinder.dreamers.entity.Dreamers;
import com.polarisfinder.dreamers.entity.UploadModel;
import com.polarisfinder.dreamers.service.DreamersService;


@Controller
@RequestMapping("dreamers")
public class DreamersController {
	@Autowired
	private DreamersService dreamersService;
	
	@Value("${polarisfinder.file.upload.dir}")
	private String polarisfinder_FILE_UPLOAD_DIR;
	
    private final Logger logger = LoggerFactory.getLogger(DreamersController.class);

    
	@PostMapping("DreamersUpload")
	public ResponseEntity<JSONObject> DreamersUpload(
	                  @RequestParam("files[]") List<MultipartFile> uploadfiles) throws Exception {
		System.out.println("hi");
        logger.debug("Multiple file upload!");

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
        try {
            abspath = saveUploadedFiles(uploadfiles);
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
    private String saveUploadedFiles(List<MultipartFile> files) throws IOException {
    	String abspath = "";
    	for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next pls
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(polarisfinder_FILE_UPLOAD_DIR +"/"+ file.getOriginalFilename());
            abspath = "/files/"+ file.getOriginalFilename();
            Files.write(path, bytes);
        }
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
    

	@PostMapping("DreamersAddContent")
	public ResponseEntity<Void> DreamersAddContent(
			@RequestParam(value="content", required = false)String content, 
			UriComponentsBuilder builder
			) throws Exception {
		
		System.out.println("content : " + content);
		
		Dreamers dreamers = new Dreamers();
		dreamers.setContent(content);
		
		boolean flag = dreamersService.createDreamers(dreamers);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("DreamersList")
	public ResponseEntity<List<Dreamers>> DreamersList(@RequestParam(
			value="id", required = false)int id,
			@RequestParam(value="paging", required = false)int paging
			) {
		System.out.println("Paging : " + paging);
		List<Dreamers> list = dreamersService.getDreamersById(id, paging);
		return new ResponseEntity<List<Dreamers>>(list, HttpStatus.OK);
	}
	
	@PostMapping("Dreamersedit")
	public ResponseEntity<Dreamers> Dreamersedit(@RequestParam(
			value="id", required = false)int id
			) {
		int paging = 0;
		List<Dreamers> list = dreamersService.getDreamersById(id, paging);
		return new ResponseEntity<Dreamers>(list.get(0), HttpStatus.OK);
	}
}
