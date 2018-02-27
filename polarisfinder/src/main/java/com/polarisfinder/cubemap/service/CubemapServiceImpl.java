package com.polarisfinder.cubemap.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.cubemap.dao.CubemapDAO;
import com.polarisfinder.cubemap.entity.Bookarng;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;
@Service
public class CubemapServiceImpl implements CubemapService {

	@Autowired
	private CubemapDAO cubemapDAO;
	

	@Override
	public synchronized boolean createCubemap(Cubemap cubemap){
		cubemapDAO.createCubemap(cubemap);
		return true;
	}

	@Override
	public synchronized boolean createStack(Stack stack){
       if (cubemapDAO.stackExists(stack.getStack_nm())) {
    	   return false;
       } else {
    	   cubemapDAO.createStack(stack);
    	   return true;
       }
	}

	@Override
	public synchronized boolean createBooksf(Booksf booksf){
		cubemapDAO.createBooksf(booksf);
		return true;
	}

	@Override
	public synchronized boolean createBookarng(Bookarng bookarng){
		cubemapDAO.createBookarng(bookarng);
		return true;
	}

	@Override
	public synchronized boolean createBox(Box box){
		cubemapDAO.createBox(box);
		return true;
	}
	
	@Override
	public List<Stack> getAllStacks() {
		return cubemapDAO.getAllStacks();
	}

	@Override
	public List<Box> getAllBoxes() {
		return cubemapDAO.getAllBoxes();
	}

	@Override
	public List<Cubemap> getCubemapsByStackId(int stack_id) {
		return cubemapDAO.getCubemapsByStackId(stack_id);
	}
	@Override
	public List<Cubemap> getCubemapsBySql(String sql) {
		return cubemapDAO.getCubemapsBySql(sql);
	}
	@Override
	public List<Booksf> getBooksfsByStackId(int stack_id) {
		return cubemapDAO.getBooksfsByStackId(stack_id);
	}
	
	@Override
	public Booksf getBooksfByBooksfId(int booksf_id) {
		return cubemapDAO.getBooksfByBooksfId(booksf_id);
	}
	
	@Override
	public Box getBoxByBoxId(int box_id) {
		return cubemapDAO.getBoxByBoxId(box_id);
	}
	
	
	@Override
	public void deleteCubemap(int stack_id) {
		cubemapDAO.deleteCubemap(stack_id);
	}
	@Override
	public void deleteBookarng(int stack_id) {
		cubemapDAO.deleteBookarng(stack_id);
	}
}
