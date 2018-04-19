package com.nasaApod.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.nasaApod.entities.Apod;
import com.nasaApod.web.services.ApodService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class ApodController {

	@Getter @Setter 
	private Apod apod;
	
	@Getter
	private Date maxCalendarDate = new Date();
	
	@ManagedProperty(value="#{apodService}")
	@Setter
	private ApodService apodService;
	
	public ApodController() {}

	@PostConstruct
	public void init() {
		getApod(null);
	}

	public void getApod(String stringDate) {
		try {
			apod = apodService.getApod(stringDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onDateSelect(SelectEvent event) {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    getApod(format.format(event.getObject()));
	}

}
