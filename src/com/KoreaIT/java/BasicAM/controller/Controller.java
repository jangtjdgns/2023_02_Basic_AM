package com.KoreaIT.java.BasicAM.controller;

import com.KoreaIT.java.BasicAM.dto.Member;

public abstract class Controller {
	protected int lastId = 0;
	protected static Member loginedMember;

	public abstract void doAction(String command, String actionMethodName);

	public abstract void makeTestDate();
}
