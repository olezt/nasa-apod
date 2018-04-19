package com.nasaApod.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.nasaApod.Constants;
import com.nasaApod.entities.Apod;
import com.nasaApod.web.services.ApodService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class ApodViewBean {

	@Getter @Setter 
	private Apod apod;
	
	@Getter
	private Date maxCalendarDate = new Date();

	private String requestedDate;
	
	@ManagedProperty(value="#{apodService}")
	@Setter
	private ApodService apodService;
	
	public ApodViewBean() {}

	@PostConstruct
	public void init() {
		//retrieve date url param if exists
		requestedDate = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(Constants.APOD_VIEW_BEAN_DATE_PARAM_NAME);
		//init apod
		getApod(requestedDate);
	}

	/**
	 * Get apod of the given date
	 * @param stringDate
	 */
	public void getApod(String stringDate) {
		try {
			apod = apodService.getApod(stringDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update apod using the selected date
	 * @param event
	 */
	public void onDateSelect(SelectEvent event) {
	    SimpleDateFormat format = new SimpleDateFormat(Constants.APOD_API_ACCEPTABLE_DATE_FORMAT);
	    getApod(format.format(event.getObject()));
	}

}
