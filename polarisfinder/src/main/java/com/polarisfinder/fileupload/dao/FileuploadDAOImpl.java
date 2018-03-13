package com.polarisfinder.fileupload.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.fileupload.entity.Fileupload;

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
