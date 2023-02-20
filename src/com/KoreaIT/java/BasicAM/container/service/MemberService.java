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

//	public boolean isInteger(int age) {
//		return memberDao.isInteger(age);
//	}
}
