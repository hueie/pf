package com.polarisfinder.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.atestpage.entity.Article;

public interface UserDAO {
	boolean createUser(User user);
	User findUserByEmail(String email);
}
