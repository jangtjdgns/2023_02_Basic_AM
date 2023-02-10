package com.KoreaIT.java.BasicAM.container;

import com.KoreaIT.java.BasicAM.dao.ArticleDao;
import com.KoreaIT.java.BasicAM.dao.MemberDao;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;

	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}
