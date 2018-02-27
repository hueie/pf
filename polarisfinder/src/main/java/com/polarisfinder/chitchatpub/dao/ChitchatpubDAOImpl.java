package com.polarisfinder.chitchatpub.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.atestpage.dao.IArticleDAO;
import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.cubemap.entity.Bookarng;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

@Transactional
@Repository
public class ChitchatpubDAOImpl  implements ChitchatpubDAO {
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public void createChitchatpub(Chitchatpub chitchatpub) {
		entityManager.persist(chitchatpub);
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Chitchatpub> getChitchatpubByPlacelocation(String placelocation, int paging) {
		String hql;
		if(placelocation == null || placelocation.equals("")) {
			System.out.println("paging : " + paging);
			hql = "FROM Chitchatpub ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);
			
			return (List<Chitchatpub>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Chitchatpub  WHERE placelocation = :placelocation ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);		
			q.setParameter("placelocation", placelocation);
			
			return (List<Chitchatpub>) q.getResultList();
		}
	}
	
}
