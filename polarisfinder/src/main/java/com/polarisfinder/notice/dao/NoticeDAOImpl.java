package com.polarisfinder.notice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.notice.entity.Notice;

@Transactional
@Repository
public class NoticeDAOImpl implements NoticeDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createNotice(Notice Notice) {
		if (Notice.getId() == 0) {
			entityManager.persist(Notice);
		} else {
			entityManager.merge(Notice);
		}
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Notice> getNoticeById(int id, int paging) {
		String hql;
		if (id == 0) {
			System.out.println("paging : " + paging);
			hql = "FROM Notice ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);

			return (List<Notice>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Notice  WHERE user_id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);
			q.setParameter("id", id);

			return (List<Notice>) q.getResultList();
		}
	}

	@Override
	public void deleteNotice(Notice Notice) {
		if (Notice.getId() != 0) {
			String hql = "delete Notice where id = :id";
			Query q = entityManager.createQuery(hql).setParameter("id", Notice.getId());
			q.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Notice> getNotice(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Notice  WHERE user_id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Notice>) q.getResultList();
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public Notice viewNotice(int id) {
		String hql;
		hql = "FROM Notice WHERE id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id);
		return (Notice)q.getSingleResult();
	}
}
