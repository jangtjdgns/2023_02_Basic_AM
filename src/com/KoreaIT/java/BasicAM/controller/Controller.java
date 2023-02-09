package com.KoreaIT.java.BasicAM.controller;

import com.KoreaIT.java.BasicAM.dto.Member;

public abstract class Controller {
	
	protected static int lastId = 0;
	
	public static Member loginedMember;

	public abstract void doAction(String command, String actionMethodName);

	public abstract void makeTestDate();

	public static boolean isLogined() {
		return loginedMember != null;
	}
}
