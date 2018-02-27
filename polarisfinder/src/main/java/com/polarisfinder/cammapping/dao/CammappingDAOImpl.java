package com.polarisfinder.cammapping.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.atestpage.dao.IArticleDAO;
import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cammapping.entity.Cam;
import com.polarisfinder.cammapping.entity.Cammapping;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

@Transactional
@Repository
public class CammappingDAOImpl  implements CammappingDAO {
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public void createCammapping(Cammapping cammapping) {
		entityManager.persist(cammapping);
	}	
	
	@Override
	public void deleteCammappingByFileuploadId(int fileupload_id, int user_id) {
		String hql = "DELETE FROM Cammapping  WHERE fileupload_id = :fileupload_id AND cammapping_user_id = :cammapping_user_id";
		entityManager.createQuery(hql).setParameter("fileupload_id", fileupload_id).setParameter("cammapping_user_id", user_id).executeUpdate();
	}	
	
	@Override
	public void updateBooksfIdToCammapping(Cammapping cammapping) {
		Cammapping cpg = (Cammapping)entityManager.find(Cammapping.class, cammapping.getCammapping_id());
		cpg.setBooksf_id(cammapping.getBooksf_id()); 
		entityManager.flush();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cam> getAllCams() {
		String hql = "FROM Cam ORDER BY cam_id DESC";
		return (List<Cam>) entityManager.createQuery(hql).getResultList();
	}	
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Cammapping> getLinesfsByFileuploadId(int fileupload_id, int user_id) {
		String hql = "FROM Cammapping  WHERE fileupload_id = :fileupload_id AND cammapping_user_id = :cammapping_user_id ORDER BY line_id ASC";
		return (List<Cammapping>) entityManager.createQuery(hql).setParameter("fileupload_id", fileupload_id).setParameter("cammapping_user_id", user_id).getResultList();
	}
	
}
