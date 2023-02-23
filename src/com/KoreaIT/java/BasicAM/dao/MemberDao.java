package com.KoreaIT.java.BasicAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.dto.Member;

public class MemberDao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}

	public void add(Member member) {
		members.add(member);
	}

	public void remove(Member member) {
		members.remove(member);
	}

	public Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		if (index == -1) {
			return null;
		}
		return members.get(index);
	}

	public boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		if (index == -1) {
			return false;
		}
		return true;
	}

	public int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Member getMember(String command, Member foundMember) {
		String[] commandBits = command.split(" ");
		if (commandBits.length == 2 || commandBits[1] == " ") {
			System.out.println("Add Name! -> member check [Name]");
			return null;
		}

		for (Member member : members) {
			if (member.name.equals(commandBits[2])) {
				return foundMember = member;
			}
		}

		System.out.printf("%s 회원은 존재하지 않습니다.\n", commandBits[2]);
		return null;
	}

	public boolean isInteger(String pNum) {
		try {
			Integer.parseInt(pNum);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean isInteger(String[] pNum) {
		for (String pnum : pNum) {
			boolean isInt = isInteger(pnum);
			if (isInt == false) {
				return false;
			}
		}
		return true;
	}

	public boolean isNoProblem(String modifyNum) {
		boolean isInt = isInteger(modifyNum);

		if (isInt) {
			int StoInt = Integer.parseInt(modifyNum);
			if (!(StoInt >= 1 && StoInt <= 4)) {
				System.out.println("1 ~ 4사이의 번호만 입력하세요.");
				return false;
			}
		}
		if (isInt == false) {
			System.out.println("제대로 된 번호를 입력해 주세요.");
			return false;
		}
		return true;
	}

}
