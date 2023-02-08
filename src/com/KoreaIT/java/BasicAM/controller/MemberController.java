package com.KoreaIT.java.BasicAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.dto.Member;
import com.KoreaIT.java.BasicAM.util.Util;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	public int lastArticleId;

	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName, int lastArticleId) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		this.lastArticleId = lastArticleId;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		}
	}

	// 회원가입
	public void doJoin() {
		int id = members.size() + 1;

		String loginId = null;
		while (true) {
			Member foundMember = null;
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (members.size() > 0) {
				for (Member member : members) {
					if (loginId.equals(member.loginId)) {
						foundMember = member;
						break;
					}
				}
			}
			if (foundMember == null) {
				break;
			}
			System.out.println("이미 사용중인 아이디 입니다.");
			continue;
		}

		String loginPw = null;
		String loginPwConfirm = null;
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 : ");
			loginPwConfirm = sc.nextLine();
			if (!(loginPw.equals(loginPwConfirm))) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, loginId, loginPw, name, Util.getNowDateStr(), Util.getNowDateStr());
		members.add(member);

		System.out.printf("%d번 회원이 가입 되었습니다\n", id);

	}
}
