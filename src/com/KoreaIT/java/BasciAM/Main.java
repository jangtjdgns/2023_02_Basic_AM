package com.KoreaIT.java.BasciAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		int a = 1; // 게시글 번호
		String 제목; // 게시글 제목
		String 내용; // 게시글 내용

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();
			
			//아무것도 입력안할 때 실행
			if(command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			// 반복문 종료
			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			} 
			
			else if (command.equals("article write")) {
				System.out.printf("제목 : ");
				제목 = sc.nextLine();
				System.out.printf("내용 : ");
				내용 = sc.nextLine();
				System.out.printf("%d번 글이 생성되었습니다.\n", a++);
			}

			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}
