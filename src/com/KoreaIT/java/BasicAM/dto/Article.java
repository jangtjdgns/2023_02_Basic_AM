package com.KoreaIT.java.BasicAM.dto;

public class Article extends Dto {
	public int hit;
	public String title;
	public String body;

	public Article(int id, String title, String body, String regDate, String updateDate) {
		this(id, 0, title, body, regDate, updateDate);
	}

	public Article(int id, int hit, String title, String body, String regDate, String updateDate) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.hit = hit;
		this.title = title;
		this.body = body;
	}
}
