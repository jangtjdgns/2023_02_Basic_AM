/* ++article list에 검색기능 추가
 * member join
 * member login
 * member logout
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
