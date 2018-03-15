package com.polarisfinder.dreamers.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.dreamers.entity.Dreamers;
import com.polarisfinder.dreamers.entity.Dreamersbookmark;
import com.polarisfinder.dreamers.entity.Dreamerscomment;
import com.polarisfinder.dreamers.entity.Dreamerslike;

@Transactional
@Repository
public class DreamersDAOImpl  implements DreamersDAO {
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public void createDreamers(Dreamers Dreamers) {
		if(Dreamers.getId() == 0){
			entityManager.persist(Dreamers);
		} else{
			entityManager.merge(Dreamers);
		}
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Dreamers> getDreamersById(int id, int paging) {
		String hql;
		if(id == 0 ) {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamers ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);
			
			return (List<Dreamers>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamers  WHERE id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);		
			q.setParameter("id", id);
			
			return (List<Dreamers>) q.getResultList();
		}
	}
	

	@Override
	public void deleteDreamers(Dreamers Dreamers) {
		if(Dreamers.getId() != 0){
			String hql = "delete Dreamers where id = :id";
		    Query q = entityManager.createQuery(hql).setParameter("id", Dreamers.getId());
		    q.executeUpdate();
		}
	}

	
	
	
	@Override
	public void createDreamerscomment(Dreamerscomment dreamerscomment) {
		entityManager.persist(dreamerscomment);
		/*
		if(Dreamerscomment.getId() == 0){
			entityManager.persist(Dreamerscomment);
		} else{
			entityManager.merge(Dreamerscomment);
		}
		*/
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Dreamerscomment> getDreamerscommentById(int dreamers_id, int paging) {
		String hql;
		if(dreamers_id == 0 ) {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamerscomment ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);
			
			return (List<Dreamerscomment>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamerscomment  WHERE dreamers_id = :dreamers_id ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);		
			q.setParameter("dreamers_id", dreamers_id);
			
			return (List<Dreamerscomment>) q.getResultList();
		}
	}

	@Override
	public void deleteDreamerscomment(Dreamerscomment Dreamerscomment) {
		if(Dreamerscomment.getDreamers_id() != 0){
			String hql = "delete Dreamerscomment where dreamers_id = :dreamers_id";
		    Query q = entityManager.createQuery(hql).setParameter("dreamers_id", Dreamerscomment.getDreamers_id());
		    q.executeUpdate();
		}
	}

	@Override
	public void createDreamerslike(Dreamerslike Dreamerslike) {
		entityManager.persist(Dreamerslike);
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Dreamerslike> getDreamerslikeById(int id, int paging) {
		String hql;
		if(id == 0 ) {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamerslike ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);
			
			return (List<Dreamerslike>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamerslike  WHERE id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);		
			q.setParameter("id", id);
			
			return (List<Dreamerslike>) q.getResultList();
		}
	}

	@Override
	public void deleteDreamerslike(Dreamerslike Dreamerslike) {
		if(Dreamerslike.getDreamers_id() != 0){
			String hql = "delete Dreamerslike where dreamers_id = :dreamers_id AND user_id = :user_id";
		    Query q = entityManager.createQuery(hql).setParameter("dreamers_id", Dreamerslike.getDreamers_id()).setParameter("user_id", Dreamerslike.getUser_id());
		    q.executeUpdate();
		}
	}
	

	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public boolean checkDreamerslike(Dreamerslike dreamerslike) {
		String hql = "FROM Dreamerslike WHERE dreamers_id = :dreamers_id AND user_id = :user_id ";
		Query q = entityManager.createQuery(hql);
		q.setParameter("dreamers_id", dreamerslike.getDreamers_id());
		q.setParameter("user_id", dreamerslike.getUser_id());
		List<Dreamerslike> dr =  q.getResultList();
		return !dr.isEmpty();
	}
	

	@Override
	public void increaseDreamerslikecnt(Dreamers Dreamers) {
		if(Dreamers.getId() != 0){
			String hql = "UPDATE Dreamers t set t.like_cnt = t.like_cnt + 1 WHERE t.id = :id";
		    Query q = entityManager.createQuery(hql).setParameter("id", Dreamers.getId());
		    q.executeUpdate();
		}
	}

	@Override
	public void decreaseDreamerslikecnt(Dreamers Dreamers) {
		if(Dreamers.getId() != 0){
			String hql = "UPDATE Dreamers t set t.like_cnt = t.like_cnt - 1 WHERE t.id = :id";
		    Query q = entityManager.createQuery(hql).setParameter("id", Dreamers.getId());
		    q.executeUpdate();
		}
	}
	
	
	@Override
	public void createDreamersbookmark(Dreamersbookmark Dreamersbookmark) {
		entityManager.persist(Dreamersbookmark);
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Dreamersbookmark> getDreamersbookmarkById(int id, int paging) {
		String hql;
		if(id == 0 ) {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamersbookmark ORDER BY id DESC ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);
			
			return (List<Dreamersbookmark>) q.getResultList();
		} else {
			System.out.println("paging : " + paging);
			hql = "FROM Dreamerslike  WHERE id = :id ";
			Query q = entityManager.createQuery(hql);
			q.setFirstResult(paging*5);
			q.setMaxResults(5);		
			q.setParameter("id", id);
			
			return (List<Dreamersbookmark>) q.getResultList();
		}
	}

	@Override
	public void deleteDreamersbookmark(Dreamersbookmark Dreamersbookmark) {
		if(Dreamersbookmark.getDreamers_id() != 0){
			String hql = "delete Dreamersbookmark where dreamers_id = :dreamers_id AND user_id = :user_id";
		    Query q = entityManager.createQuery(hql).setParameter("dreamers_id", Dreamersbookmark.getDreamers_id()).setParameter("user_id", Dreamersbookmark.getUser_id());
		    q.executeUpdate();
		}
	}
	

	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public boolean checkDreamersbookmark(Dreamersbookmark dreamersbookmark) {
		String hql = "FROM Dreamersbookmark WHERE dreamers_id = :dreamers_id AND user_id = :user_id ";
		Query q = entityManager.createQuery(hql);
		q.setParameter("dreamers_id", dreamersbookmark.getDreamers_id());
		q.setParameter("user_id", dreamersbookmark.getUser_id());
		List<Dreamersbookmark> dr =  q.getResultList();
		return !dr.isEmpty();
	}
	

	@Override
	public void increaseDreamersbookmarkcnt(Dreamers Dreamers) {
		if(Dreamers.getId() != 0){
			String hql = "UPDATE Dreamers t set t.bookmark_cnt = t.bookmark_cnt + 1 WHERE t.id = :id";
		    Query q = entityManager.createQuery(hql).setParameter("id", Dreamers.getId());
		    q.executeUpdate();
		}
	}

	@Override
	public void decreaseDreamersbookmarkcnt(Dreamers Dreamers) {
		if(Dreamers.getId() != 0){
			String hql = "UPDATE Dreamers t set t.bookmark_cnt = t.bookmark_cnt - 1 WHERE t.id = :id";
		    Query q = entityManager.createQuery(hql).setParameter("id", Dreamers.getId());
		    q.executeUpdate();
		}
	}
}
