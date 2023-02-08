/* ++MemberController와 ArticleController를 도입하고 기능을 실행하는 역할을 controller로 이전
 * ==프로그램 시작==
 * test 게시물 3개 생성 
 * 명령어) 입력
 * member join -> MemberController로 이전
 * -> 다른 기능들 추후 구현 예정(입력없을때, 특수문자, detail시 작성자 추가 등)
 * article list -> ArticleController로 이전
 * article write -> ArticleController로 이전
 * article detail [int] -> ArticleController로 이전
 * article delete [int] -> ArticleController로 이전
 * article modify [int] -> ArticleController로 이전
 * system exit
 * ==프로그램 종료== */

package com.KoreaIT.java.BasicAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.controller.ArticleController;
import com.KoreaIT.java.BasicAM.controller.Controller;
import com.KoreaIT.java.BasicAM.controller.MemberController;
import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.dto.Member;
import com.KoreaIT.java.BasicAM.util.Util;

public class App {
	public static List<Article> articles;
	public static List<Member> members;
	public static int lastArticleId;
	static {
		members = new ArrayList<>();
		articles = new ArrayList<>();
		lastArticleId = 0;
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestDate();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

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

			String[] commandBits = command.split(" ");
			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];

			if (commandBits.length == 1) {
				System.out.println("명령어 확인 후 다시 입력해주세요");
			}

			Controller controller = null;
			if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("article")) {
				controller = articleController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			controller.doAction(command, actionMethodName, lastArticleId);
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}

	public static void makeTestDate() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		articles.add(new Article(lastArticleId++ + 1, 11, "t1", "test1", Util.getNowDateStr(), Util.getNowDateStr()));
		articles.add(new Article(lastArticleId++ + 1, 22, "t2", "test2", Util.getNowDateStr(), Util.getNowDateStr()));
		articles.add(new Article(lastArticleId++ + 1, 33, "t3", "test1", Util.getNowDateStr(), Util.getNowDateStr()));
	}
}
