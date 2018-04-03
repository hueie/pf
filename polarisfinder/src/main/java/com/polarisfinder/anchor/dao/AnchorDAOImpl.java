package com.polarisfinder.anchor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.anchor.entity.Anchor;

@Transactional
@Repository
public class AnchorDAOImpl implements AnchorDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createAnchor(Anchor Anchor) {
		if (Anchor.getId() == 0) {
			entityManager.persist(Anchor);
		} else {
			entityManager.merge(Anchor);
		}
	}

	
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public Anchor viewAnchor(int id) {
		String hql;
		hql = "FROM Anchor WHERE id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id);
		return (Anchor)q.getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Anchor> getAnchorById(int id, int paging) {
		String hql;
		if (id == 0) {
			System.out.println("paging : " + paging);
			hql = "FROM Anchor ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);

			return (List<Anchor>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Anchor  WHERE send_user_id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);
			q.setParameter("id", id);

			return (List<Anchor>) q.getResultList();
		}
	}

	@Override
	public void deleteAnchor(Anchor Anchor) {
		if (Anchor.getId() != 0) {
			String hql = "delete Anchor where id = :id";
			Query q = entityManager.createQuery(hql).setParameter("id", Anchor.getId());
			q.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Anchor> getAnchor(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Anchor  WHERE user_id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Anchor>) q.getResultList();
	}

	
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public void updateAnchor(int id, boolean anchor) {
		String hql;
		if(anchor){
			hql = "update Anchor set star = 1 WHERE id = :id";
		} else {
			hql = "update Anchor set star = 0 WHERE id = :id";
		}
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id);
		q.executeUpdate();
	}
}
