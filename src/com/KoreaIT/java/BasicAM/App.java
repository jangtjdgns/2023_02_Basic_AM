/* ++ 댓글 기능 구현 중
 * ? | help
 * member join
 * member login
 * member logout
 * member delete
 * member check
 * member modify
 * article list
 * article write
 * article detail [int]
 * article delete [int]
 * article modify [int] */

package com.KoreaIT.java.BasicAM;

import java.util.Scanner;

import com.KoreaIT.java.BasicAM.controller.ArticleController;
import com.KoreaIT.java.BasicAM.controller.Controller;
import com.KoreaIT.java.BasicAM.controller.MemberController;

public class App {

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		memberController.makeTestDate();
		articleController.makeTestDate();

		System.out.println("게시글 명령어 보기 (? or help)");

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("?") || command.equals("help")) {
				System.out.printf(
						"+------------------[참고 사항]-------------------+\n"
					+ "|                                                |\n"
					+ "|           원할한 명령어 사용을 위해            |\n"
					+ "|         회원가입을 먼저 진행 해주시고          |\n"
					+ "|     로그인 후 사용하시는것을 추천드립니다.     |\n"
					+ "| *로그인을 하지 않을 경우 제약사항이 많습니다.* |\n"
					+ "|                                                |\n"
					+ "+-----------------[기본 명령어]------------------+\n"
					+ "|                                                |\n"
					+ "|  게시글 목록  |  article list                  |\n"
					+ "|  게시글 보기  |  article detail [게시글 번호]  |\n"
					+ "|  회 원 조 회  |  member chack [회원 이름]      |\n"
					+ "|                                                |\n"
					+ "+---------[로그인 후 이용가능한 명령어]----------+\n"
					+ "|                                                |\n"
					+ "|  게시글 쓰기  |  article write                 |\n"
					+ "|  게시글 삭제  |  article delete [게시글 번호]  |\n"
					+ "|  게시글 수정  |  article modify [게시글 번호]  |\n"
					+ "|  로 그 아 웃  |  member logout                 |\n"
					+ "|  계 정 삭 제  |  member delete                 |\n"
					+ "|  회 원 조 회  |  member modify      						|\n"
					+ "|                                                |\n"
					+ "+--------[로그아웃 후 이용가능한 명령어]---------+\n"
					+ "|                                                |\n"
					+ "|  로  그  인   |  member login                  |\n"
					+ "|  회 원 가 입  |  member join                   |\n"
					+ "|                                                |\n"
					+ "+------------------------------------------------+\n");
				continue;
			}

			String[] commandBits = command.split(" ");

			if (commandBits.length == 1) {
				System.out.println("명령어 확인 후 다시 입력해주세요");
				continue;
			}

			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];

			Controller controller = null;
			if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("article")) {
				controller = articleController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}

			String actionName = controllerName + " " + actionMethodName;
			switch (actionName) {
			case "article write":
			case "article modify":
			case "article delete":
			case "member logout":
			case "member delete":
			case "member modify":
				if (Controller.isLogined() == false) {
					System.out.println("로그인 후 이용해 주세요");
					continue;
				}
				break;
			case "member login":
			case "member join":
				if (Controller.isLogined()) {
					System.out.println("로그아웃 후 이용해 주세요");
					continue;
				}
				break;
			}

			controller.doAction(command, actionMethodName);
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}
