package com.KoreaIT.java.BasicAM.controller;

import com.KoreaIT.java.BasicAM.dto.Member;

public abstract class Controller {

	public static Member loginedMember;

	public static int maxTestId = 10;
	
	public abstract void doAction(String command, String actionMethodName);

	public abstract void makeTestDate();

	public static boolean isLogined() {
		return loginedMember != null;
	}
}
