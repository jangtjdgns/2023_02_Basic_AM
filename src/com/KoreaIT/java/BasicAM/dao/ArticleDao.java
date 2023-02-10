package com.KoreaIT.java.BasicAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BasicAM.dto.Article;

public class ArticleDao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}
}
