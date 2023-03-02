package com.KoreaIT.java.BasicAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.dto.Member;
import com.KoreaIT.java.BasicAM.dto.Reply;

public class ReplyDao {
	public List<Reply> replies;
	public ReplyDao() {
		replies = new ArrayList<>();
	}

	public void add(Reply reply) {
		replies.add(reply);
	}

	public Reply getReplyByArticleId(Reply reply, Article foundArticle) {
		for (Reply Reply : replies) {
			if (Reply.articleId == foundArticle.id) {
				return reply = Reply;
			}
		}
		return reply;
	}

	public Reply getReplyByMemberId(Reply reply, int memberId) {
		for (Reply Reply : replies) {
			if (Reply.id == memberId) {
				return reply = replies.get(memberId);
			}
		}
		return reply;
	}

	public String getReplyAuthor(Reply reply, String replyAuthor) {	
		List<Member> members = Container.memberDao.members;
		for (Member member : members) {
			if (member.id == reply.memberId) {
				return replyAuthor = member.name;
			}
		}
		return null;
	}

}
