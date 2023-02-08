package com.KoreaIT.java.BasicAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.util.Util;

public class ArticleController extends Controller {
	private List<Article> articles;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	public int lastArticleId;

	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName, int lastArticleId) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		this.lastArticleId = lastArticleId;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "write":
			doWrite();
			break;
		case "detail":
			showDetail();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
		}
	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}

		System.out.println("번호  |     제목     |  조회");
		String tempTitle = null;

		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			if (article.title.length() > 6) {
				tempTitle = article.title.substring(0, 6);
				System.out.printf("  %2d  |  %4s  |  %2d\n", article.id, tempTitle + " ...", article.hit);
				continue;
			}
			System.out.printf("  %2d  |  %6s      |  %2d\n", article.id, article.title, article.hit);
		}
	}

	public void doWrite() {
		int id = lastArticleId + 1;
		String regDate = Util.getNowDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, title, body, regDate, regDate);
		articles.add(article);

		System.out.printf("%d번 글이 생성 되었습니다\n", id);
		lastArticleId++;
	}

	public void showDetail() {
		Article foundArticle = null;
		foundArticle = getArticle(command, foundArticle);
		if (foundArticle != null) {
			foundArticle.hit++;
			System.out.printf("번호 : %d\n", foundArticle.id);
			System.out.printf("조회 : %d\n", foundArticle.hit);
			System.out.printf("작성 날짜 : %s\n", foundArticle.regDate);
			System.out.printf("수정 날짜 : %s\n", foundArticle.updateDate);
			System.out.printf("제목 : %s\n", foundArticle.title);
			System.out.printf("내용 : %s\n", foundArticle.body);

		}
	}

	public void doDelete() {
		// 5. 글 삭제
		Article foundArticle = null;
		foundArticle = getArticle(command, foundArticle);
		if (foundArticle != null) {
			System.out.printf("%d번 게시물이 삭제 되었습니다.\n", foundArticle.id);
			articles.remove(foundArticle);
		}
	}

	public void doModify() {
		Article foundArticle = null;
		foundArticle = getArticle(command, foundArticle);
		if (foundArticle != null) {
			System.out.printf("%d번 게시물을 수정합니다.\n", foundArticle.id);
			System.out.printf("제목 : ");
			foundArticle.title = sc.nextLine();
			System.out.printf("내용 : ");
			foundArticle.body = sc.nextLine();
			foundArticle.updateDate = Util.getNowDateStr();
			System.out.println("글이 수정 되었습니다.");
		}
	}

	public Article getArticle(String a, Article b) {
		String[] commandBits = a.split(" ");
		int id = Util.getStrtoInt(commandBits[2]);
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
}
