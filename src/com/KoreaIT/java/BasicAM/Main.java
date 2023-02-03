/* ==프로그램 시작==
 * 명령어) 입력
 * article list
 * article write
 * article detail [int]
 * article delete [int]
 * system exit
 * ==프로그램 종료== */

package com.KoreaIT.java.BasicAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			// 입력 없을때
			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			// 명령어 잘못입력시
			if (command.equals("article detail") || command.equals("article delete")) {
				System.out.println("Add int -> article [command] [int]");
				continue;
			}

			// 프로그램 종료
			if (command.equals("system exit")) {
				break;
			}

			// 1. 게시글 확인
			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}

				System.out.println("번호 |  제목");
				String tempTitle = null;

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					if (article.title.length() > 6) {
						tempTitle = article.title.substring(0, 6);
						System.out.printf("  %d  |  %s...\n", article.id, tempTitle);
						continue;
					}
					System.out.printf("  %d  |  %s\n", article.id, article.title);
				}
			}

			// 2. 글 조회
			else if (command.startsWith("article detail ")) {

				String[] ID = command.split(" ");

				int id = Util.isNum(ID[2]); // 예외처리
				if (id == 0) {
					System.out.println("error -> article detail [int]");
					continue;
				}

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%s번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.DateTimelog);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
			}

			// 3. 글 생성
			else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				String regDate = Util.getNowDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body, regDate);
				articles.add(article);

				System.out.printf("%d번 글이 생성 되었습니다\n", id);
				lastArticleId++;
			}

			// 4. 글 삭제
			else if (command.startsWith("article delete ")) {
				String[] ID = command.split(" ");

				int id = Util.isNum(ID[2]); // 예외처리
				if (id == 0) {
					System.out.println("error -> article delete [int]");
					continue;
				}

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("%d번 게시물이 삭제 되었습니다.\n", id);
				articles.remove(foundArticle);
			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}

class Article {
	int id;
	String title;
	String body;
	String DateTimelog;

	Article(int id, String title, String body, String DateTimelog) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.DateTimelog = DateTimelog;
	}
}