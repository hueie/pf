package com.polarisfinder.task.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.task.entity.Task;

@Transactional
@Repository
public class TaskDAOImpl implements TaskDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createTask(Task Task) {
		if (Task.getId() == 0) {
			entityManager.persist(Task);
		} else {
			entityManager.merge(Task);
		}
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Task> getTaskById(int id, int paging) {
		String hql;
		if (id == 0) {
			System.out.println("paging : " + paging);
			hql = "FROM Task ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);

			return (List<Task>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Task  WHERE user_id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);
			q.setParameter("id", id);

			return (List<Task>) q.getResultList();
		}
	}

	@Override
	public void deleteTask(Task Task) {
		if (Task.getId() != 0) {
			String hql = "delete Task where id = :id";
			Query q = entityManager.createQuery(hql).setParameter("id", Task.getId());
			q.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Task> getTask(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Task  WHERE user_id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Task>) q.getResultList();
	}


}
