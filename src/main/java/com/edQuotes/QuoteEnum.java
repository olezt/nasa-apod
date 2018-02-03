package com.edQuotes;

import java.util.Date;
import java.util.Random;

public enum QuoteEnum {
	BOOBOONATO(1, "boubounato", Constants.USER_BOO, "25/12/2017 12:52"),
	BOO_COOL(2, "cool", Constants.USER_BOO, "25/12/2017 12:52"),
	THA_TO_FIX_EGO(3, "tha to fix ego", Constants.USER_BOO, "25/12/2017 12:52");
	
	int id;
	String text;
	String user;
	String date;
	
	private QuoteEnum(int id, String text, String user, String date) {
		this.id = id;
		this.text = text;
		this.user = user;
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public String getUser() {
		return user;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDate() {
		return date;
	}

	public static QuoteEnum getRandomQuote(){
		Random r = new Random();
		r.setSeed(new Date().getTime());
		
		int quoteEnumLength = QuoteEnum.values().length;
		int randomId = r.nextInt(quoteEnumLength) + 1;
		QuoteEnum randomQuoteEnum = null;
		for(QuoteEnum qe: QuoteEnum.values()) {
			if(qe.getId() == randomId){
				randomQuoteEnum = qe;
				break;
			}
		}
		return randomQuoteEnum;
	}
	
}