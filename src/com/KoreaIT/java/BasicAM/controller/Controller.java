package com.KoreaIT.java.BasicAM.controller;

public abstract class Controller {
	protected int lastId = 0;

	public abstract void doAction(String command, String actionMethodName);
	public abstract void makeTestDate();
}
