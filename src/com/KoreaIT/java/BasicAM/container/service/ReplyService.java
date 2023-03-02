package com.KoreaIT.java.BasicAM.container.service;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dao.ReplyDao;
import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.dto.Reply;

public class ReplyService {
	private ReplyDao replyDao;

	public ReplyService() {
		this.replyDao = Container.replyDao;
	}

	public void add(Reply reply) {
		replyDao.add(reply);
	}

	public Reply getReplyByArticleId(Reply reply, Article foundArticle) {
		return replyDao.getReplyByArticleId(reply, foundArticle);
	}

	public Reply getReplyByMemberId(Reply reply, int memberId) {
		return replyDao.getReplyByMemberId(reply, memberId);
	}
	
	public String getReplyAuthor(Reply reply, String replyAuthor) {
		return replyDao.getReplyAuthor(reply, replyAuthor);
	}
}
