package com.nasaApod.controllers;

import java.io.Serializable;
import java.text.ParseException;
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

import com.nasaApod.Constants;
import com.nasaApod.entities.Apod;
import com.nasaApod.web.services.ApodService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class ApodListViewBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter @Setter 
	private List<Apod> apodList = new ArrayList<Apod>();
	
	@ManagedProperty(value="#{apodService}")
	@Setter
	private ApodService apodService;
	
	//Date of the last loaded apod
	private Date lastLoadedApodDate;
	
	public ApodListViewBean() {}

	@PostConstruct
	public void init() {
		SimpleDateFormat format = new SimpleDateFormat(Constants.APOD_API_ACCEPTABLE_DATE_FORMAT);
		lastLoadedApodDate = getDateXDaysFromYDate(new Date(), -30);
		//initialize apodList with the latest 30 apods
		updateApodList(format.format(lastLoadedApodDate), format.format(new Date()));
	}

	/**
	 * Update apodList with the apods between the given dates
	 * @param startStringDate
	 * @param endStringDate
	 */
	public void updateApodList(String startStringDate,String endStringDate) {
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
	
	/**
	 * Returns a Date object X days +- from the given Y Date
	 * @param yDate
	 * @param xDays
	 * @return
	 */
	private Date getDateXDaysFromYDate(Date yDate, int xDays) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(yDate);
		cal.add(Calendar.DAY_OF_MONTH, xDays);
		Date resultDate = cal.getTime();
		return resultDate;
	}
	
	/**
	 * Update apodList, loading 30 more apods starting from the last one
	 * @throws ParseException
	 */
	public void loadMoreApods() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(Constants.APOD_API_ACCEPTABLE_DATE_FORMAT);

		//get as end date a day before last loaded date
		Date endDate = getDateXDaysFromYDate(lastLoadedApodDate, -1);
		//update lastLoadedDate as 30 days before previous lastLoadedDate
		lastLoadedApodDate = getDateXDaysFromYDate(lastLoadedApodDate, -30);

		updateApodList(format.format(lastLoadedApodDate), format.format(endDate));
	}
}
