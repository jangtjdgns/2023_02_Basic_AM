package com.KoreaIT.java.BasicAM.Dto;

public class Article {
		public int id;
		public int hit;
		public String title;
		public String body;
		public String regDate;
		public String updateDate = "";

		public Article(int id, String title, String body, String regDate) {
			this.id = id;
			this.hit = 0;
			this.title = title;
			this.body = body;
			this.regDate = regDate;
		}
}
