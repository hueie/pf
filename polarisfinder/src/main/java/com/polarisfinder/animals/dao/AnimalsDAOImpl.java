package com.polarisfinder.animals.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.animals.entity.Animals;

@Transactional
@Repository
public class AnimalsDAOImpl implements AnimalsDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createAnimals(Animals Animals) {
		if (Animals.getAnimals_id() == 0) {
			entityManager.persist(Animals);
		} else {
			entityManager.merge(Animals);
		}
	}

	
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public Animals viewAnimals(int id) {
		String hql;
		hql = "FROM Animals WHERE id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id);
		return (Animals)q.getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Animals> getAnimalsById(int id, int paging) {
		String hql;
		if (id == 0) {
			System.out.println("paging : " + paging);
			hql = "FROM Animals ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);

			return (List<Animals>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Animals  WHERE send_user_id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);
			q.setParameter("id", id);

			return (List<Animals>) q.getResultList();
		}
	}

	@Override
	public void deleteAnimals(Animals Animals) {
		if (Animals.getAnimals_id() != 0) {
			String hql = "delete Animals where id = :id";
			Query q = entityManager.createQuery(hql).setParameter("id", Animals.getAnimals_id());
			q.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Animals> getAnimals(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Animals  WHERE user_id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Animals>) q.getResultList();
	}

	
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public void updateAnimals(int id, boolean Animals) {
		String hql;
		if(Animals){
			hql = "update Animals set star = 1 WHERE id = :id";
		} else {
			hql = "update Animals set star = 0 WHERE id = :id";
		}
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id);
		q.executeUpdate();
	}
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Animals> getRandomAnimals(){
		String hql;
		hql = "FROM Animals ORDER BY RAND() ";
		Query q = entityManager.createQuery(hql);
		q.setMaxResults(1);
		
		return q.getResultList();
	}
}
