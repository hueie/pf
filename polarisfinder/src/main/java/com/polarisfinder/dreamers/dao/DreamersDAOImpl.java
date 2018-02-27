package com.polarisfinder.dreamers.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.dreamers.entity.Dreamers;

@Transactional
@Repository
public class DreamersDAOImpl  implements DreamersDAO {
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public void createDreamers(Dreamers Dreamers) {
		entityManager.persist(Dreamers);
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Dreamers> getDreamersById(int id, int paging) {
		String hql;
		if(id == 0 ) {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamers ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);
			
			return (List<Dreamers>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamers  WHERE id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);		
			q.setParameter("id", id);
			
			return (List<Dreamers>) q.getResultList();
		}
	}
	
}
