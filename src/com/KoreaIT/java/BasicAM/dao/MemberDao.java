package com.KoreaIT.java.BasicAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BasicAM.dto.Member;

public class MemberDao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}
}
