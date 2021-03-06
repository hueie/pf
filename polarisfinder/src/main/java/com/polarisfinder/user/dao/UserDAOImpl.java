package com.polarisfinder.user.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.user.entity.Role;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public User findById(int id) {
		//Hibernate Inner Join 
		String hql = "From User u join u.roles r where u.user_id = :user_id";
		Query q = entityManager.createQuery(hql);
		q.setParameter("user_id", id);
		
		User user = null;
		List<Object[]> lst = (List<Object[]>) q.getResultList();
		for (Object[] result : lst) {
			user = (User) result[0];
		    Role userRole = (Role) result[1];
			System.out.println("User : "+user.getUsername() + " Role : "+userRole.getRole());
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		}
		
		return user;  
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByUserName(String username) {
		//Hibernate Inner Join 
		String hql = "From User u join u.roles r where u.username = :username";
		Query q = entityManager.createQuery(hql);
		q.setParameter("username", username);
		
		User user = null;
		List<Object[]> lst = (List<Object[]>) q.getResultList();
		for (Object[] result : lst) {
			user = (User) result[0];
		    Role userRole = (Role) result[1];
			System.out.println("User : "+user.getUsername() + " Role : "+userRole.getRole());
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		}
		
		return user;  
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByOauthid(String type, String oauthid) {
		//Hibernate Inner Join 
		String hql = "From User u join u.roles r where u.type = :type AND u.oauthid = :oauthid";
		Query q = entityManager.createQuery(hql);
		q.setParameter("type", type);
		q.setParameter("oauthid", oauthid);
		
		User user = null;
		List<Object[]> lst = (List<Object[]>) q.getResultList();
		for (Object[] result : lst) {
			user = (User) result[0];
		    Role userRole = (Role) result[1];
			System.out.println("User : "+user.getUsername() + " Role : "+userRole.getRole());
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		}
		
		return user;  
	}
}
