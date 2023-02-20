package com.KoreaIT.java.BasicAM.dto;

public class Member extends Dto {
	public String loginId;
	public String loginPw;
	public String name;
	public String gender;
	public int age;
	public String phoneNumber;
	public String address;

	public Member(int id, String regDate, String updateDate, String loginId, String loginPw,
			String name, String gender, int age, String phoneNumber, String address) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
}
