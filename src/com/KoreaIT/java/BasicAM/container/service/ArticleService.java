package com.KoreaIT.java.BasicAM.container.service;

import java.util.List;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dao.ArticleDao;
import com.KoreaIT.java.BasicAM.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public void add(Article article) {
		articleDao.add(article);
	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}

	public Article getArticle(String command, Article foundArticle) {
		return articleDao.getArticle(command, foundArticle);
	}

	public List<Article> getArticles() {
		return articleDao.articles;
	}

	public String foundNameInMember(Article article) {
		return articleDao.foundNameInMember(article);
	}
}
