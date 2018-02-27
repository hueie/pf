package com.polarisfinder.fileupload.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.atestpage.dao.IArticleDAO;
import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cammapping.entity.Cammapping;
import com.polarisfinder.fileupload.entity.Fileupload;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

@Transactional
@Repository
public class FileuploadDAOImpl  implements FileuploadDAO {
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public void createFileupload(Fileupload fileupload) {
		entityManager.persist(fileupload);
	}	
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Fileupload> getFilesByFileuploadRegId(int fileupload_reg_id) {
		String hql = "FROM Fileupload  WHERE fileupload_reg_id = :fileupload_reg_id ORDER BY fileupload_id DESC";
		return (List<Fileupload>) entityManager.createQuery(hql).setParameter("fileupload_reg_id", fileupload_reg_id).getResultList();
	}
}
