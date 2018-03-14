package com.polarisfinder.chitchatpub.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.chitchatpub.entity.Chitchatpub;

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
	public List<Chitchatpub> getChitchatpubByPlacelocation(Chitchatpub chitchatpub, int paging) {
		String hql;
		if(chitchatpub.getPlacelatitude() == null || chitchatpub.getPlacelatitude().equals("")) {
			System.out.println("paging : " + paging);
			hql = "FROM Chitchatpub ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);
			
			return (List<Chitchatpub>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Chitchatpub  WHERE placelatitude = :placelatitude AND placelongitude = :placelongitude ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);		
			q.setParameter("placelatitude", chitchatpub.getPlacelatitude());
			q.setParameter("placelongitude", chitchatpub.getPlacelongitude());
			
			return (List<Chitchatpub>) q.getResultList();
		}
	}
	
}
