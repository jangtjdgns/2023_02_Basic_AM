package com.KoreaIT.java.BasicAM.dto;

public class Member extends Dto{
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
