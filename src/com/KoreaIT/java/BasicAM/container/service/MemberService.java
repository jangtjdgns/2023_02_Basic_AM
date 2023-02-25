package com.KoreaIT.java.BasicAM.container.service;

import java.util.List;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dao.MemberDao;
import com.KoreaIT.java.BasicAM.dto.Member;

public class MemberService {
	MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public List<Member> getMembers() {
		return memberDao.members;
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public void remove(Member member) {
		memberDao.remove(member);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}

	public Member getMember(String command, Member foundMember) {
		return memberDao.getMember(command, foundMember);
	}

	public boolean isInteger(String pNum) {
		return memberDao.isInteger(pNum);
	}

	public boolean isInteger(String[] pNum) {
		return memberDao.isInteger(pNum);
	}

	public boolean isNoProblem(String modifyNum) {
		return memberDao.isNoProblem(modifyNum);
	}

	public String checkLoginPw(String LoginPw, String LoginPwConfirm) {
		return memberDao.checkLoginPw(LoginPw, LoginPwConfirm);
	}

	public String checkName(String memberName) {
		return memberDao.checkName(memberName);
	}

	public String checkPhoneNumber(String PhoneNumber) {
		return memberDao.checkPhoneNumber(PhoneNumber);
	}

	public String checkAddress(String Address) {
		return memberDao.checkAddress(Address);
	}

}
