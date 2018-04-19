package com.nasaApod.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.nasaApod.entities.Apod;
import com.nasaApod.web.services.ApodService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class ApodListViewBean {

	@Getter @Setter 
	private List<Apod> apodList = new ArrayList<Apod>();
	
	@ManagedProperty(value="#{apodService}")
	@Setter
	private ApodService apodService;
	
	public ApodListViewBean() {}

	@PostConstruct
	public void init() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = getDateXDaysBeforeYDate(new Date(), 30);

		getApodList(format.format(startDate), format.format(new Date()));
	}

	public void getApodList(String startStringDate,String endStringDate) {
		try {
			List<Apod> tempApodList = apodService.getApodList(startStringDate, endStringDate);
			//reverse the order of the newly returned list, in order to have the latest date as first
			Collections.reverse(tempApodList);
			apodList.addAll(tempApodList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Date getDateXDaysBeforeYDate(Date yDate, int xDays) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(yDate);
		cal.add(Calendar.DAY_OF_MONTH, -xDays);
		Date resultDate = cal.getTime();
		return resultDate;
	}
	
}
