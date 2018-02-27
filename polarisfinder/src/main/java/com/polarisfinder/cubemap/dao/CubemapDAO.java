package com.polarisfinder.cubemap.dao;

import java.util.List;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cubemap.entity.Bookarng;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

public interface CubemapDAO {

    List<Stack> getAllStacks();
    List<Box> getAllBoxes();
    void createCubemap(Cubemap cubemap);
    void createStack(Stack stack);
    void createBooksf(Booksf booksf);
    void createBookarng(Bookarng bookarng);
    void createBox(Box box);
    boolean stackExists(String stackNm);
	List<Cubemap> getCubemapsByStackId(int stack_id);
	List<Cubemap> getCubemapsBySql(String sql);
	
	List<Booksf> getBooksfsByStackId(int stack_id);

	Booksf getBooksfByBooksfId(int booksf_id);
	Box getBoxByBoxId(int box_id);

    void deleteCubemap(int stack_id);
    void deleteBookarng(int stack_id);
}
