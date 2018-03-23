package com.polarisfinder.chitchatpub.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.chitchatpub.entity.Chitchatpubstar;

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
	public Chitchatpub getChitchatpubById(int chitchatpub_id){
		String hql;
		hql = "FROM Chitchatpub WHERE id = :chitchatpub_id ";
		Query q = entityManager.createQuery(hql);
		q.setParameter("chitchatpub_id", chitchatpub_id);
		
		return (Chitchatpub) q.getSingleResult();
	}
	
	public Chitchatpubstar getChitchatpubstar(Chitchatpubstar chitchatpubstar){
		String hql;
		hql = "FROM Chitchatpubstar WHERE chitchatpub_id = :chitchatpub_id AND user_id = :user_id ";
		Query q = entityManager.createQuery(hql);
		q.setParameter("chitchatpub_id", chitchatpubstar.getChitchatpub_id());
		q.setParameter("user_id", chitchatpubstar.getUser_id());
		
		return (Chitchatpubstar) q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Chitchatpub> getChitchatpubByPlacelocation(Chitchatpub chitchatpub, int paging) {
		String hql;
		if(chitchatpub.getPlacelatitude() == 0 && chitchatpub.getPlacelongitude() == 0) {
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

	@Override
	public void createChitchatpubstar(Chitchatpubstar chitchatpubstar) {
		entityManager.persist(chitchatpubstar);
	}

	@Override
	public void increaseChitchatpubstarcnt(Chitchatpub chitchatpub) {
		String hql = "UPDATE Chitchatpub t set t.star_cnt = t.star_cnt "+chitchatpub.getStar_cnt()+" WHERE t.id = :id";
		Query q = entityManager.createQuery(hql).setParameter("id", chitchatpub.getId());
		q.executeUpdate();
	}
	
}
