package com.KoreaIT.java.BasicAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BasicAM.dto.Member;

public class MemberDao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}

	public void add(Member member) {
		members.add(member);
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

	public boolean isEmptyMemberInformation(String blank) {
		if (blank == "") {
			return true;
		}
		return false;
	}
}
