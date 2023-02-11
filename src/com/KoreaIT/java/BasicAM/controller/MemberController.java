package com.KoreaIT.java.BasicAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dto.Member;
import com.KoreaIT.java.BasicAM.util.Util;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private int loginCount = 0;

	public MemberController(Scanner sc) {
		this.members = Container.memberService.getMembers();
		this.sc = sc;
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
			if(loginCount == 5) {
				System.out.println("로그인 시도 횟수를 초과하였습니다.");
				loginCount = 0;
				return;
			}
			System.out.printf("로그인 아이디 : ");
			String loginId = sc.nextLine().trim();
			System.out.printf("로그인 비밀번호 : ");
			String loginPw = sc.nextLine().trim();

			if (Container.memberService.isEmptyMemberInformation(loginId)
					|| Container.memberService.isEmptyMemberInformation(loginPw)) {
				System.out.println("아이디는 필수정보 입니다.");
				loginCount++;
				continue;
			}

			Member member = Container.memberService.getMemberByLoginId(loginId);
			if (member == null) {
				System.out.println("해당 회원은 존재하지 않습니다");
				loginCount++;
				continue;
			}
			if (!(member.loginPw.equals(loginPw))) {
				System.out.println("비밀번호를 다시 입력해주세요");
				loginCount++;
				continue;
			}
			loginedMember = member;
			loginCount = 0;
			break;
		}
		System.out.printf("로그인 되었습니다. %s님 환영합니다!\n", loginedMember.name);
	}

	private void doJoin() {
		int id = members.size() + 1;
		String loginId = null;
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();

			if (Container.memberService.isEmptyMemberInformation(loginId)) {
				System.out.println("아이디는 필수정보 입니다.");
				continue;
			}

			if (Container.memberService.isJoinableLoginId(loginId)) {
				System.out.println("이미 사용중인 아이디 입니다.");
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			System.out.printf("비밀번호 재확인 : ");
			loginPwConfirm = sc.nextLine().trim();
			if (Container.memberService.isEmptyMemberInformation(loginPw)
					|| Container.memberService.isEmptyMemberInformation(loginPwConfirm)) {
				System.out.println("비밀번호는 필수정보 입니다.");
				continue;
			}
			if (!(loginPw.equals(loginPwConfirm))) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}

		String name = null;
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			if (Container.memberService.isEmptyMemberInformation(name)) {
				System.out.println("이름은 필수정보 입니다.");
				continue;
			}
			break;
		}

		Member member = new Member(id, loginId, loginPw, name, Util.getNowDateStr(), Util.getNowDateStr());
		members.add(member);

		System.out.printf("%d번 회원이 가입 되었습니다\n", id);
	}

	public void makeTestDate() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다");
		Container.memberService.add(new Member(1, "admin", "admin", "관리자", Util.getNowDateStr(), Util.getNowDateStr()));
		Container.memberService.add(new Member(2, "test1", "test1", "회원1", Util.getNowDateStr(), Util.getNowDateStr()));
		Container.memberService.add(new Member(3, "test2", "test2", "회원2", Util.getNowDateStr(), Util.getNowDateStr()));
	}
}
