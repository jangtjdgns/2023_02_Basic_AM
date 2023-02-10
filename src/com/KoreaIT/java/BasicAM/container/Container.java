package com.KoreaIT.java.BasicAM.container;

import com.KoreaIT.java.BasicAM.container.service.ArticleService;
import com.KoreaIT.java.BasicAM.container.service.MemberService;
import com.KoreaIT.java.BasicAM.dao.ArticleDao;
import com.KoreaIT.java.BasicAM.dao.MemberDao;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;

	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}
}
