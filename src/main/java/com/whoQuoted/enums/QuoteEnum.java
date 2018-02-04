package com.whoQuoted.enums;

import java.util.Date;
import java.util.Random;

import com.whoQuoted.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum QuoteEnum {
	BOOBOONATO(1, "boubounato", Constants.USER_BOO, "25/12/2017 12:52"),
	BOO_COOL(2, "cool", Constants.USER_BOO, "25/12/2017 12:52"),
	THA_TO_FIX_EGO(3, "tha to fix ego", Constants.USER_BOO, "25/12/2017 12:52");
	
	@Getter final int id;
	@Getter final String text;
	@Getter final String user;
	@Getter  final String date;
	

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