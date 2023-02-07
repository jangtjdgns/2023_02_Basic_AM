package com.KoreaIT.java.BasicAM.Dto;

public class Article {
		public int id;
		public int hit;
		public String title;
		public String body;
		public String regDate;
		public String updateDate;

		public Article(int id, String title, String body, String regDate, String updateDate) {
			this(id, 0, title, body, regDate, updateDate);
		}
		public Article(int id, int hit, String title, String body, String regDate, String updateDate) {
			this.id = id;
			this.hit = hit;
			this.title = title;
			this.body = body;
			this.regDate = regDate;
		}
}
