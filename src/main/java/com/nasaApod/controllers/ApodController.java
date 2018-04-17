package com.nasaApod.controllers;

import java.text.SimpleDateFormat;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.nasaApod.entities.Apod;
import com.nasaApod.web.services.ApodService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
public class ApodController {

	@Getter @Setter private Apod apod;
	
	@Autowired
	ApodService apodService;
	
	public ApodController() {
		getApod(null);
	}

	public void getApod(String stringDate) {
		try {
			System.out.println(stringDate);
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
