package com.KoreaIT.java.BasciAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();
			
			
			//반복문 종료
			if (command.equals("system exit")) {
				break;
			}
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}
