package com.KoreaIT.java.BasicAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.dto.Member;
import com.KoreaIT.java.BasicAM.util.Util;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	public void makeTestDate() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다");
		members.add(new Member(1, "admin", "admin", "admin", Util.getNowDateStr(), Util.getNowDateStr()));
		members.add(new Member(2, "test1", "test1", "admin", Util.getNowDateStr(), Util.getNowDateStr()));
		members.add(new Member(3, "test2", "test2", "admin", Util.getNowDateStr(), Util.getNowDateStr()));
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되었습니다");
	}

	private void doLogin() {
		while (true) {
			System.out.printf("로그인 아이디 : ");
			String loginId = sc.nextLine();
			System.out.printf("로그인 비밀번호 : ");
			String loginPw = sc.nextLine();

			Member member = getMemberByLoginId(loginId);
			if (member == null) {
				System.out.println("해당 회원은 존재하지 않습니다");
				continue;
			}
			if (!(member.loginPw.equals(loginPw))) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			loginedMember = member;
			break;
		}
		System.out.println("로그인 되었습니다.");
	}

	private void doJoin() {
		int id = members.size() + 1;
		String loginId = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
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

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		if (index == -1) {
			return null;
		}
		return members.get(index);
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		if (index == -1) {
			return false;
		}
		return true;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
}
