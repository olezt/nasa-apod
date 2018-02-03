package com.edQuotes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
//import javax.faces.event.ActionEvent;

@ManagedBean
public class Quote {

	private String text;
	private String user;
	private String userLowerCase;
	private Date date;
  
	public Quote() {
		initRandomQuote();
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserLowerCase() {
		return userLowerCase;
	}

	public void setUserLowerCase(String userLowerCase) {
		this.userLowerCase = userLowerCase;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDateFromString(String stringDate) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM");
		Date date = null;
		try {
			date = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setDate(date);
	}
	
	
	public void initRandomQuote(){
		QuoteEnum qe = QuoteEnum.getRandomQuote();
		setText(qe.getText());
		setUser(qe.getUser());
		setUserLowerCase(qe.getUser().toLowerCase());
		setDateFromString(qe.getDate());
	}

}
