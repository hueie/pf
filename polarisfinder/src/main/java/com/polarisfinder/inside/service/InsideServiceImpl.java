package com.polarisfinder.inside.service;

import java.util.List;

import com.polarisfinder.atestpage.entity.Article;

public interface InsideServiceImpl {
	Article getArticleById(int articleId);
    
	/*
     List<Article> getAllArticles();
     boolean createArticle(Article article);
     void updateArticle(Article article);
     void deleteArticle(int articleId);
     */
}
