package com.KoreaIT.java.BasicAM;

//조회
//삭제
//모두삭제
//명령어 종류

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
			String command = sc.nextLine().trim(); // trum() -> 양쪽 끝 공백을 없앰
			
			//글 조회
			String[] s = command.split(" ");
			String[] sN = new String[s.length];
			int Stringtoint = 0;
			for(int i = 0; i < s.length; i++) {
				sN[i] = s[i];
				if(s.length == 2) {
					Stringtoint = Integer.parseInt(sN[2]);
				}
			}
			
			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			// 게시글 확인
			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				
				System.out.println("번호 |  제목");
				String tempTitle = null;
				
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article Get = articles.get(i);
					if(Get.title.length() > 4) {
						tempTitle = Get.title.substring(0, 5);
						System.out.printf("  %d  |  %s...\n", Get.id, tempTitle);
						continue;
					}
					System.out.printf("  %d  |  %s\n", Get.id, Get.title);

				}
			}

			// 글 생성
			else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);
				articles.add(article);

				System.out.printf("%d번 글이 생성 되었습니다\n", id);
				lastArticleId++;
			}
			
			else if(command.equals("article detail " + sN[2])) {
				Article dGet = articles.get(Stringtoint - 1);
			}
			
			//글 삭제
			else if(command.equals("article delete " + sN[2])) {
				Article Del = articles.remove(Stringtoint - 1);
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

	Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}