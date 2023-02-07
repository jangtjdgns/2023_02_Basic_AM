package com.KoreaIT.java.BasicAM.Dto;

public class Member {
	public int id;
	public String regDate;
	public String updateDate;
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(int id, String loginId, String loginPw, String name, String regDate, String updateDate) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;	
	}
}
