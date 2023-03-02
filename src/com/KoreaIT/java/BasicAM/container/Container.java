package com.KoreaIT.java.BasicAM.container;

import com.KoreaIT.java.BasicAM.container.service.ArticleService;
import com.KoreaIT.java.BasicAM.container.service.MemberService;
import com.KoreaIT.java.BasicAM.container.service.ReplyService;
import com.KoreaIT.java.BasicAM.dao.ArticleDao;
import com.KoreaIT.java.BasicAM.dao.MemberDao;
import com.KoreaIT.java.BasicAM.dao.ReplyDao;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ReplyDao replyDao;
	
	public static ArticleService articleService;
	public static MemberService memberService;
	public static ReplyService replyService;
	

	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		replyDao = new ReplyDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
		replyService = new ReplyService();
	}
}
