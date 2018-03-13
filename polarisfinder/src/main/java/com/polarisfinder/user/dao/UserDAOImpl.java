package com.polarisfinder.user.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.user.entity.User;

@Transactional
@Repository
public class UserDAOImpl  implements UserDAO {
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public boolean createUser(User user) {
		if (!entityManager.contains(user)) {
			entityManager.persist(user);
			return true;
		} else {
			//em.merge(entity);
			return false;
		}
	}	
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public User findUserByEmail(String email) {
		String hql = "FROM User  WHERE email = :email";
		return (User) entityManager.createQuery(hql).setParameter("email", email).getSingleResult();
	}
	
}
