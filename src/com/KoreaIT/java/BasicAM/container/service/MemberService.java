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

	public Member getMemberByLoginId(String loginId) {
		return getMemberByLoginId(loginId);
	}

	public boolean isJoinableLoginId(String loginId) {
		return isJoinableLoginId(loginId);
	}
}
