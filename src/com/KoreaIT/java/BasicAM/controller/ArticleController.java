package com.KoreaIT.java.BasicAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.util.Util;

public class ArticleController extends Controller {
	private List<Article> articles;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private int lastArticleId;

	public ArticleController(Scanner sc) {
		this.articles = Container.articleService.getArticles();
		this.lastArticleId = 0;
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

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
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	private void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}

		System.out.println(" 번호 |   제 목   |  조회  | 작성자");
		String tempTitle = null;

		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			String writeName = Container.articleService.foundNameInMember(article);

			if (article.title.length() > 6) {
				tempTitle = article.title.substring(0, 6);
				System.out.printf("  %2d  |  %4s  |  %3d   |  %3s\n", article.id, tempTitle + " ...", article.hit, writeName);
				continue;
			}
			System.out.printf("  %2d  |   %4s    |  %3d   |  %3s\n", article.id, article.title, article.hit, writeName);
		}
	}

	private void doWrite() {
		int id = lastArticleId + 1;
		String title = null;
		String body = null;

		while (true) {
			System.out.printf("제목 : ");
			title = sc.nextLine().trim();
			if (Container.articleService.isEmptyArticle(title)) {
				System.out.println("제목을 입력하세요.");
				continue;
			}
			break;
		}
		while (true) {
			System.out.printf("내용 : ");
			body = sc.nextLine().trim();
			if (Container.articleService.isEmptyArticle(body)) {
				System.out.println("내용을 입력하세요.");
				continue;
			}
			break;
		}

		Article article = new Article(id, loginedMember.id, title, body, Util.getNowDateStr(), Util.getNowDateStr());
		articles.add(article);

		System.out.printf("%d번 글이 생성 되었습니다\n", id);
		lastArticleId++;
	}

	private void showDetail() {
		Article foundArticle = null;
		foundArticle = Container.articleService.getArticle(command, foundArticle);

		if (foundArticle != null) {
			String writeName = Container.articleService.foundNameInMember(foundArticle);

			foundArticle.hit++;
			System.out.printf("번호 : %d\n", foundArticle.id);
			System.out.printf("조회 : %d\n", foundArticle.hit);
			System.out.printf("작성 날짜 : %s\n", foundArticle.regDate);
			System.out.printf("수정 날짜 : %s\n", foundArticle.updateDate);
			System.out.printf("작성자 : %s\n", writeName);
			System.out.printf("제목 : %s\n", foundArticle.title);
			System.out.printf("내용 : %s\n", foundArticle.body);
		}
	}

	private void doModify() {
		Article foundArticle = null;
		foundArticle = Container.articleService.getArticle(command, foundArticle);

		if (foundArticle != null && foundArticle.memberId != loginedMember.id) {
			System.out.println("해당 글에 권한이 없습니다.");
			return;
		}

		if (foundArticle != null) {
			System.out.printf("%d번 게시물을 수정합니다.\n", foundArticle.id);
			while (true) {
				System.out.printf("제목 : ");
				foundArticle.title = sc.nextLine().trim();
				if (Container.articleService.isEmptyArticle(foundArticle.title)) {
					System.out.println("제목을 입력하세요.");
					continue;
				}
				break;
			}
			while (true) {
				System.out.printf("내용 : ");
				foundArticle.body = sc.nextLine().trim();
				if (Container.articleService.isEmptyArticle(foundArticle.body)) {
					System.out.println("내용을 입력하세요.");
					continue;
				}
				break;
			}
			foundArticle.updateDate = Util.getNowDateStr();
			System.out.println("글이 수정 되었습니다.");
		}
	}

	private void doDelete() {
		Article foundArticle = null;
		foundArticle = Container.articleService.getArticle(command, foundArticle);

		if (foundArticle != null && foundArticle.memberId != loginedMember.id) {
			System.out.println("해당 글에 권한이 없습니다.");
			return;
		}

		if (foundArticle != null) {
			System.out.printf("%d번 게시물이 삭제 되었습니다.\n", foundArticle.id);
			articles.remove(foundArticle);
		}
	}

	public void makeTestDate() {
		System.out.println("테스트를 위한 글 데이터를 생성합니다");
		Container.articleService
				.add(new Article(lastArticleId++ + 1, 11, 1, "t1", "test1", Util.getNowDateStr(), Util.getNowDateStr()));
		Container.articleService
				.add(new Article(lastArticleId++ + 1, 22, 2, "t2", "test2", Util.getNowDateStr(), Util.getNowDateStr()));
		Container.articleService
				.add(new Article(lastArticleId++ + 1, 33, 3, "t3", "test1", Util.getNowDateStr(), Util.getNowDateStr()));
	}
}
