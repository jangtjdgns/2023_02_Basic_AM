package com.KoreaIT.java.BasicAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.dto.Member;

public class ArticleDao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
	}
	
	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public Article getArticle(String a, Article b) {
		String[] commandBits = a.split(" ");
		if (commandBits.length == 2) {
			System.out.println("Add integer! -> article [command] [int]");
			return b;
		}
		int id = getStrtoInt(commandBits[2]);
		if (id == 0) {
			return b;
		}

		for (Article article : articles) {
			if (article.id == id) {
				return b = article;
			}
		}

		System.out.printf("%s번 게시물은 존재하지 않습니다.\n", id);
		return b;
	}

	public String foundNameInMember(Article article) {
		List<Member> members = Container.memberDao.members;
		for (Member member : members) {
			if (article.memberId == member.id) {
				return member.name;
			}
		}
		return null;
	}

	private static int getStrtoInt(String str) {
		int id = 0;
		try {
			return id = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.out.println("Not integer! -> article [command] [int]");
			return id;
		}
	}

	public boolean isEmptyArticle(String blank) {
		if (blank == "") {
			return true;
		}
		return false;
	}
}
