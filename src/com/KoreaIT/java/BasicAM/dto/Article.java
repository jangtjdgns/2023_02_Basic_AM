package com.KoreaIT.java.BasicAM.dto;

public class Article extends Dto {
	public int hit;
	public String title;
	public String body;
	public int memberId;

	public Article(int id, int memberId, String title, String body, String regDate, String updateDate) {
		this(id, 0, memberId, title, body, regDate, updateDate);
	}

	public Article(int id, int hit, int memberId, String title, String body, String regDate, String updateDate) {
		this.id = id;
		this.hit = hit;
		this.memberId = memberId;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}
}
