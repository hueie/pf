package com.polarisfinder.follow.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.follow.entity.Follow;

@Transactional
@Repository
public class FollowDAOImpl implements FollowDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createFollow(Follow Follow) {
		if (Follow.getId() == 0) {
			entityManager.persist(Follow);
		} else {
			entityManager.merge(Follow);
		}
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Follow> getFollowById(int id, int paging) {
		String hql;
		if (id == 0) {
			System.out.println("paging : " + paging);
			hql = "FROM Follow ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);

			return (List<Follow>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Follow  WHERE send_user_id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);
			q.setParameter("id", id);

			return (List<Follow>) q.getResultList();
		}
	}

	@Override
	public void deleteFollow(Follow Follow) {
		if (Follow.getId() != 0) {
			String hql = "delete Follow where id = :id";
			Query q = entityManager.createQuery(hql).setParameter("id", Follow.getId());
			q.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Follow> getFollow(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Follow  WHERE to_user_id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Follow>) q.getResultList();
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Follow> getFollowSent(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Follow  WHERE send_user_id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Follow>) q.getResultList();
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Follow> getFollowStarred(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Follow  WHERE to_user_id = :id and star = 1 ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Follow>) q.getResultList();
	}

}
