/* ++members, articles, makeTestData를 각각의 컨트롤러로 이전
 *  ==프로그램 시작==
 * test 게시물 3개 생성 
 * 명령어) 입력
 * member join
 * article list
 * article write
 * article detail [int]
 * article delete [int]
 * article modify [int]
 * system exit
 * ==프로그램 종료== */

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
			controller.doAction(command, actionMethodName);
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}
