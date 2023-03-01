package com.KoreaIT.java.BasicAM.dto;

public class Reply extends Dto{
	public int memberId;
	public int articleId;
	public String replyBody;
	
	public Reply(int id, int memberId, int articleId, String replyBody, String regDate, String updateDate) {
		this.id = id;
		this.memberId = memberId;
		this.articleId = articleId;
		this.replyBody = replyBody;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}
}
