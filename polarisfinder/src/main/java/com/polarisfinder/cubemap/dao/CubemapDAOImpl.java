package com.polarisfinder.cubemap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polarisfinder.atestpage.dao.IArticleDAO;
import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cubemap.entity.Bookarng;
import com.polarisfinder.cubemap.entity.Booksf;
import com.polarisfinder.cubemap.entity.Box;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

@Transactional
@Repository
public class CubemapDAOImpl  implements CubemapDAO {
	@PersistenceContext	
	private EntityManager entityManager;	

	@SuppressWarnings("unchecked")
	@Override
	public List<Stack> getAllStacks() {
		String hql = "FROM Stack ORDER BY stack_id DESC";
		return (List<Stack>) entityManager.createQuery(hql).getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Box> getAllBoxes() {
		String hql = "FROM Box ORDER BY box_id DESC";
		return (List<Box>) entityManager.createQuery(hql).getResultList();
	}
	@Override
	public void createCubemap(Cubemap cubemap) {
		entityManager.persist(cubemap);
	}	
	@Override
	public void createStack(Stack stack) {
		entityManager.persist(stack);
	}
	@Override
	public void createBooksf(Booksf booksf) {
		entityManager.persist(booksf);
	}
	@Override
	public void createBookarng(Bookarng bookarng) {
		entityManager.persist(bookarng);
	}
	@Override
	public void createBox(Box box) {
		entityManager.persist(box);
	}
	
	@Override
	public boolean stackExists(String stack_nm) {
		String hql = "FROM Stack WHERE stack_nm = :stack_nm "; //this Table Name Is Class Name Not Real Table name
		int count = entityManager.createQuery(hql).setParameter("stack_nm", stack_nm).getResultList().size();
		return count > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Cubemap> getCubemapsByStackId(int stack_id) {
		String hql = "FROM Cubemap  WHERE stack_id = :stack_id ORDER BY stack_id DESC";
		return (List<Cubemap>) entityManager.createQuery(hql).setParameter("stack_id", stack_id).getResultList();
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Cubemap> getCubemapsBySql(String sql) {
		return (List<Cubemap>) entityManager.createQuery(sql).getResultList();
	}

	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public List<Booksf> getBooksfsByStackId(int stack_id) {
		String hql = "FROM Booksf  WHERE stack_id = :stack_id ORDER BY stack_id DESC";
		return (List<Booksf>) entityManager.createQuery(hql).setParameter("stack_id", stack_id).getResultList();
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public Booksf getBooksfByBooksfId(int booksf_id) {
		return (Booksf) entityManager.find(Booksf.class, booksf_id);
	}
	
	@SuppressWarnings("unchecked") //Ignore Warnings
	@Override
	public Box getBoxByBoxId(int box_id) {
		return (Box) entityManager.find(Box.class, box_id);
	}
	
	
	@Override
	public void deleteCubemap(int stack_id) {
		String hql = "DELETE FROM Cubemap  WHERE stack_id = :stack_id ";
		entityManager.createQuery(hql).setParameter("stack_id", stack_id).executeUpdate();
	}
	
	@Override
	public void deleteBookarng(int stack_id) {
		String hql = "DELETE FROM Bookarng  WHERE stack_id = :stack_id ";
		entityManager.createQuery(hql).setParameter("stack_id", stack_id).executeUpdate();
	}
}
