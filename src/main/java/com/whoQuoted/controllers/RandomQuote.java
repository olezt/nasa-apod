package com.whoQuoted.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;

import com.whoQuoted.enums.QuoteEnum;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
public class RandomQuote {

	@Getter @Setter private String text;
	@Getter @Setter private String user;
	@Getter @Setter private String userLowerCase;
	@Getter @Setter private Date date;
  
	public RandomQuote() {
		initRandomQuote();
	}

	public void setDateFromString(String stringDate) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dateFormat.setTimeZone(getGreekTimeZone());
		Date date = null;
		try {
			date = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setDate(date);
	}
	
	public TimeZone getGreekTimeZone(){
		TimeZone tz = TimeZone.getTimeZone("Athens/Greece");
		return tz;
	}
	
	public void initRandomQuote(){
		QuoteEnum qe = QuoteEnum.getRandomQuote();
		setText(qe.getText());
		setUser(qe.getUser());
		setUserLowerCase(qe.getUser().toLowerCase());
		setDateFromString(qe.getDate());
	}

}
