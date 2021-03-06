package com.polarisfinder.message.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.message.entity.Message;

@Transactional
@Repository
public class MessageDAOImpl implements MessageDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createMessage(Message Message) {
		if (Message.getId() == 0) {
			entityManager.persist(Message);
		} else {
			entityManager.merge(Message);
		}
	}

	
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public Message viewMessage(int id) {
		String hql;
		hql = "FROM Message WHERE id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id);
		return (Message)q.getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Message> getMessageById(int id, int paging) {
		String hql;
		if (id == 0) {
			System.out.println("paging : " + paging);
			hql = "FROM Message ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);

			return (List<Message>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Message  WHERE send_user_id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging * 5);
			q.setMaxResults(5);
			q.setParameter("id", id);

			return (List<Message>) q.getResultList();
		}
	}

	@Override
	public void deleteMessage(Message Message) {
		if (Message.getId() != 0) {
			String hql = "delete Message where id = :id";
			Query q = entityManager.createQuery(hql).setParameter("id", Message.getId());
			q.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Message> getMessage(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Message  WHERE to_user_id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Message>) q.getResultList();
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Message> getMessageSent(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Message  WHERE send_user_id = :id ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Message>) q.getResultList();
	}

	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public List<Message> getMessageStarred(int id, int paging) {
		String hql;
		System.out.println("paging : " + paging);
		hql = "FROM Message  WHERE to_user_id = :id and star = 1 ";
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(paging * 5);
		q.setMaxResults(5);
		q.setParameter("id", id);

		return (List<Message>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked") // Ignore Warnings
	@Override
	public void updateStarred(int id, boolean star) {
		String hql;
		if(star){
			hql = "update Message set star = 1 WHERE id = :id";
		} else {
			hql = "update Message set star = 0 WHERE id = :id";
		}
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id);
		q.executeUpdate();
	}
}
