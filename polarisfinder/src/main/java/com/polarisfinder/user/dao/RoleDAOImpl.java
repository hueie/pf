package com.polarisfinder.user.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.atestpage.dao.IArticleDAO;
import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;
import com.polarisfinder.user.entity.Role;

@Transactional
@Repository
public class RoleDAOImpl  implements RoleDAO {
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public void createRole(Role role) {
		entityManager.persist(role);
	}	
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public Role findByRole(String role) {
		String hql = "FROM Role  WHERE role = :role";
		return (Role) entityManager.createQuery(hql).setParameter("role", role).getSingleResult();
	}
	
}
