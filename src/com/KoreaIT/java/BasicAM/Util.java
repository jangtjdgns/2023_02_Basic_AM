package com.KoreaIT.java.BasicAM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	/** 현재 날짜 시간 String */
	public static String getNowDateStr() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date now = new Date();
		
		return sdf1.format(now);
	}

	/** 정수 변환 예외 처리 Try Catch */
	public static int getStrtoInt(String str) {
		int id = 0;
		
		try {
			return id = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.out.println("Not integer! -> article [command] [int]");
			return id;
		}
	}
	
}
