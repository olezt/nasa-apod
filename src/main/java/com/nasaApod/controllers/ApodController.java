package com.nasaApod.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.nasaApod.entities.Apod;
import com.nasaApod.web.services.ApodService;

//import com.nasaApod.enums.QuoteEnum;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
public class ApodController {

	@Getter @Setter private Apod apod;
	
	@Autowired
	ApodService apodService;
	
	public ApodController() {
//		initRandomQuote();
		try {
			apod = apodService.getApod(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//
//	public void setDateFromString(String stringDate) {
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		dateFormat.setTimeZone(getGreekTimeZone());
//		Date date = null;
//		try {
//			date = dateFormat.parse(stringDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		setDate(date);
//	}
//	
//	public TimeZone getGreekTimeZone(){
//		TimeZone tz = TimeZone.getTimeZone("Athens/Greece");
//		return tz;
//	}
//	
//	public void initRandomQuote(){
//		QuoteEnum qe = QuoteEnum.getRandomQuote();
//		setText(qe.getText());
//		setUser(qe.getUser());
//		setUserLowerCase(qe.getUser().toLowerCase());
//		setDateFromString(qe.getDate());
//	}

}
