package com.KoreaIT.java.BasicAM.dto;

public class Reply extends Dto{
	public String memberId;
	public String articleId;
	public String replyBody;
	
	public Reply(int id, String memberId, String articleId, String replyBody, String regDate, String updateDate) {
		this.id = id;
		this.memberId = memberId;
		this.articleId = articleId;
		this.replyBody = replyBody;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}
}
