/* ++해당 글의 작성자만 수정/삭제 기능 구현 (권한체크)
 *  ==프로그램 시작==
 * 명령어) 입력
 * member join
 * member login
 * member logout
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