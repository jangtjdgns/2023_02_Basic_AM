/* ==프로그램 시작==
 * test 게시물 3개 생성 
 * 명령어) 입력
 * article list
 * article write
 * article detail [int]
 * article delete [int]
 * article modify [int]
 * system exit
 * ==프로그램 종료== */

package com.KoreaIT.java.BasicAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static List<Article> articles = new ArrayList<>();
	public static int lastArticleId = 0;

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		makeTestDate();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			// 입력 없을때
			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			// 명령어 잘못입력시
			if (command.equals("article detail") || command.equals("article delete") || command.equals("article modify")) {
				System.out.println("Add integer! -> article [command] [int]");
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

				System.out.println("번호 |     제목     |  조회");
				String tempTitle = null;

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					if (article.title.length() > 6) {
						tempTitle = article.title.substring(0, 6);
						System.out.printf("  %d  |  %4s  |  %2s\n", article.id, tempTitle + " ...", article.hit);
						continue;
					}
					System.out.printf("  %d  |  %6s      |  %2s\n", article.id, article.title, article.hit);
				}
			}

			// 2. 글 생성
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

			// 3. 글 조회
			else if (command.startsWith("article detail ")) {
				Article foundArticle = null;
				foundArticle = check(command, foundArticle);
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

			// 4. 글 삭제
			else if (command.startsWith("article delete ")) {
				Article foundArticle = null;
				foundArticle = check(command, foundArticle);
				if (foundArticle != null) {
					System.out.printf("%d번 게시물이 삭제 되었습니다.\n", foundArticle.id);
					articles.remove(foundArticle);
				}
			}

			// 5. 글 수정
			else if (command.startsWith("article modify ")) {
				Article foundArticle = null;
				foundArticle = check(command, foundArticle);
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

			else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}

	public static void makeTestDate() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		for (int i = 0; i < 3; i++) {
			int id = lastArticleId + 1;
			String regDate = Util.getNowDateStr();
			String title = "t" + Integer.toString(i + 1);
			String body = "t" + Integer.toString(i + 1);
			Article article = new Article(id, title, body, regDate);
			articles.add(article);
			lastArticleId++;
		}
	}

	public static Article check(String a, Article b) {
		String[] commandBits = a.split(" ");
		int id = Util.getStrtoInt(commandBits[2]);
		if (id == 0) {
			return b;
		}

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return b = article;
			}
		}
		System.out.printf("%s번 게시물은 존재하지 않습니다.\n", id);
		return b;
	}
}

class Article {
	int id;
	int hit;
	String title;
	String body;
	String regDate;
	String updateDate = "";

	Article(int id, String title, String body, String regDate) {
		this.id = id;
		this.hit = 0;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
	}
}