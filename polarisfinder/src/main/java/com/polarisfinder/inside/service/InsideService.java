package com.polarisfinder.inside.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.atestpage.dao.IArticleDAO;
import com.polarisfinder.atestpage.entity.Article;
@Service
public class InsideService implements InsideServiceImpl {
	@Autowired
	private IArticleDAO articleDAO;
	@Override
	public Article getArticleById(int articleId) {
		Article obj = articleDAO.getArticleById(articleId);
		return obj;
	}	
	/*
	@Override
	public List<Article> getAllArticles(){
		return articleDAO.getAllArticles();
	}
	@Override
	public synchronized boolean createArticle(Article article){
       if (articleDAO.articleExists(article.getTitle(), article.getCategory())) {
    	   return false;
       } else {
    	   articleDAO.createArticle(article);
    	   return true;
       }
	}
	@Override
	public void updateArticle(Article article) {
		articleDAO.updateArticle(article);
	}
	@Override
	public void deleteArticle(int articleId) {
		articleDAO.deleteArticle(articleId);
	}
	*/
}
