package com.polarisfinder.cubemap.controller;

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
import com.polarisfinder.cubemap.entity.Bookarng;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;
import com.polarisfinder.cubemap.service.CubemapService;

@Controller
@RequestMapping("cubemap")
public class CubemapController {
	@Autowired
	private CubemapService cubemapService;
	
	@GetMapping("Cubemap")
	public ResponseEntity<List<Cubemap>> Cubemap (@RequestParam(value="stack_id", required = false)int stack_id) {
		System.out.println("stack_id : " + stack_id);
		List<Cubemap> list = null;
		if (stack_id != 0 ) {
			list = cubemapService.getCubemapsByStackId(stack_id);
			return new ResponseEntity<List<Cubemap>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<Cubemap>>(list, HttpStatus.OK);
		}
	}
	
	@GetMapping("CubemapStackList")
	public ResponseEntity<List<Stack>> getAllStacks() {
		List<Stack> list = cubemapService.getAllStacks();
		return new ResponseEntity<List<Stack>>(list, HttpStatus.OK);
	}
	
	@GetMapping("CubemapBooksfList")
	public ResponseEntity<List<Booksf>> CubemapBooksfList(@RequestParam(value="stack_id", required = false)int stack_id) {
		List<Booksf> list = cubemapService.getBooksfsByStackId(stack_id);
		return new ResponseEntity<List<Booksf>>(list, HttpStatus.OK);
	}
	
	@PostMapping("CubemapAddStack")
	public ResponseEntity<Void> CubemapAddStack(@RequestParam(value="stack_nm", required = false)String stack_nm, 
			@RequestParam(value="stack_remk", required = false)String stack_remk, UriComponentsBuilder builder
			) throws Exception {
		System.out.println("CubemapAddStack");
		int stackId = 0;
		
		Stack stack = new Stack();
		stack.setStack_id(stackId);
		stack.setStack_nm(stack_nm);
		stack.setStack_remk(stack_remk);
		
		boolean flag = cubemapService.createStack(stack);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/cubemap/Cubemap?stack_id={stack_id}").buildAndExpand(stack.getStack_id()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	@PostMapping("CubemapAddBooksf")
	public ResponseEntity<Void> CubemapAddBooksf(@RequestParam(value="stack_id", required = false)int stack_id, 
			@RequestParam(value="booksf_nm", required = false)String booksf_nm, 
			@RequestParam(value="booksf_remk", required = false)String booksf_remk, 
			@RequestParam(value="booksf_y", required = false)int booksf_y,
			@RequestParam(value="booksf_x", required = false)int booksf_x,
			@RequestParam(value="booksf_z", required = false)int booksf_z,
			@RequestParam(value="booksf_flw", required = false)int booksf_flw) {
		
		Booksf booksf = new Booksf();
		booksf.setStack_id(stack_id);
		booksf.setBooksf_nm(booksf_nm);
		booksf.setBooksf_remk(booksf_remk);
		booksf.setBooksf_y(booksf_y);
		booksf.setBooksf_x(booksf_x);
		booksf.setBooksf_z(booksf_z);
		booksf.setBooksf_flw(booksf_flw);
		
		boolean flag = cubemapService.createBooksf(booksf);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@PostMapping("CubemapAddBox")
	public ResponseEntity<Void> CubemapAddBox( 
			@RequestParam(value="box_nm", required = false)String box_nm, 
			@RequestParam(value="box_remk", required = false)String box_remk
			) {
		
		Box box = new Box();
		box.setBox_nm(box_nm);
		box.setBox_remk(box_remk);
		
		boolean flag = cubemapService.createBox(box);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@PostMapping("CubemapBoxList")
	public ResponseEntity<List<Box>> getAllBoxes() {
		List<Box> list = cubemapService.getAllBoxes();
		return new ResponseEntity<List<Box>>(list, HttpStatus.OK);
	}
	
	
	@PostMapping("CubemapBooksfView")
	public ResponseEntity<Booksf> CubemapBooksfView(@RequestParam(value="booksf_id", required = false)int booksf_id) {
		Booksf list = cubemapService.getBooksfByBooksfId(booksf_id);
		return new ResponseEntity<Booksf>(list, HttpStatus.OK);
	}
	
	@PostMapping("CubemapBoxView")
	public ResponseEntity<Box> CubemapBoxView(@RequestParam(value="box_id", required = false)int box_id) {
		Box list = cubemapService.getBoxByBoxId(box_id);
		return new ResponseEntity<Box>(list, HttpStatus.OK);
	}	
	
	@PostMapping("CubemapSavemap")
	public ResponseEntity<Void> CubemapSavemap(@RequestParam(value="cube_list", required = false)String cube_list, 
			@RequestParam(value="stack_id", required = false)int stack_id) {
		
		//Clear Stack
		cubemapService.deleteCubemap(stack_id);
        
		//Create Monuments
		try {
			JSONObject obj = new JSONObject(cube_list);
			JSONArray items = obj.getJSONArray("cube_list");

			for (int i = 0; i < items.length(); i++) {
	            JSONObject item = items.getJSONObject(i);
	            int cube_idx = item.getInt("cube_idx");
	            int pos_x = item.getInt("pos_x");
	            int pos_y = item.getInt("pos_y");
	            int pos_z = item.getInt("pos_z");
	            int object_id = item.getInt("object_id");
	            int cube_type = item.getInt("cube_type");
	            int linked_id = item.getInt("linked_id");
	            int cube_size = item.getInt("cube_size");
	            int cube_axis = item.getInt("cube_axis");
			
	            Cubemap cubemap = new Cubemap();
	            cubemap.setCube_idx(cube_idx);
	            cubemap.setStack_id(stack_id);

	            cubemap.setPos_x(pos_x);
	            cubemap.setPos_y(pos_y);
	            cubemap.setPos_z(pos_z);
	            cubemap.setObject_id(object_id);
	            cubemap.setCube_type(cube_type);
	            cubemap.setLinked_id(linked_id);
	            cubemap.setCube_size(cube_size);
	            cubemap.setCube_axis(cube_axis);
			            
	            boolean flag = cubemapService.createCubemap(cubemap);
	            if (flag == false) {
	            	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	            }
			}
		} catch(Exception e) {
				
		}
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}	
	
	@PostMapping("CubemapSavestack")
	public ResponseEntity<Void> CubemapSavestack(@RequestParam(value="stackId", required = false)int stackId 
			) {
		//Clear Stack
		cubemapService.deleteBookarng(stackId);
		
		
		String sql = " FROM Cubemap where stack_id = "+stackId+" and cube_type = 7 order by object_id, cube_axis, pos_y";
		List<Cubemap> list = cubemapService.getCubemapsBySql(sql);
		
		int obejctId=0, booksfId=0;
		int posY, posX, posZ;

		int flw_idx=0, preFlwPosY=0;
		int minPosY=9999, maxPosY=-9999, minPosX=9999, maxPosX=-9999, minPosZ=9999, maxPosZ=-9999; 
		Cubemap preCube = new Cubemap();
		preCube.setObject_id(-1);
		for (Cubemap cube : list) {
			if(cube.getObject_id() != preCube.getObject_id()) {
				//Init Process
				flw_idx=0;
				minPosY=9999; maxPosY=-9999; minPosX=9999; maxPosX=-9999; minPosZ=9999; maxPosZ=-9999; 
				obejctId = cube.getObject_id();
				booksfId = cube.getLinked_id();
				System.out.println("obejctId : "+obejctId+" booksfId : "+booksfId);
			}
			if(cube.getCube_axis() == 1) {
				posY = cube.getPos_y();
				if(posY > maxPosY){
					maxPosY = posY;
				}
				if(posY < minPosY) {
					minPosY = posY;
				}
			} else if(cube.getCube_axis() == 2) {
				posZ = cube.getPos_z();
				if(posZ > maxPosZ){
					maxPosZ = posZ;
				}
				if(posZ < minPosZ){
					minPosZ = posZ;
				}
			} else if(cube.getCube_axis() == 3) {
				posX = cube.getPos_x();
				if(posX > maxPosX){
					maxPosX = posX;
				}
				if(posX < minPosX){
					minPosX = posX;
				}
			} else if(cube.getCube_axis() == 4) {
				if(flw_idx != 0) {
					sql = " FROM Cubemap where stack_id = "+stackId+" and cube_type = 1"+ 
							" AND pos_y between "+preFlwPosY+" and "+cube.getPos_y()+
							" AND pos_x between "+minPosX+" and "+maxPosX+
							" AND pos_z between "+minPosZ+" and "+maxPosZ;
					System.out.println("Insert Into BookArng : "+sql);
					List<Cubemap> list2 = cubemapService.getCubemapsBySql(sql);
					int boxId = 0, arngId = 0, box_pos_y = 0;
					for (Cubemap cube2 : list2) {
						boxId = cube2.getLinked_id();
						box_pos_y = cube2.getPos_y();

						Bookarng bookarng = new Bookarng();
						bookarng.setBox_id(boxId);
						bookarng.setStack_id(stackId);
						bookarng.setBooksf_id(booksfId);
						bookarng.setBooksf_f_no(flw_idx);
						bookarng.setBooksf_r_no(0);
						bookarng.setBooksf_r_sno(0);
						bookarng.setArng_cd(1);
						
						boolean flag = cubemapService.createBookarng(bookarng);
					}
				}
				flw_idx++;
			} 
			preFlwPosY = cube.getPos_y();
			preCube = cube;
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}	
}
