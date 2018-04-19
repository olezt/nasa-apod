package com.nasaApod.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import lombok.Setter;

@ManagedBean
@RequestScoped
public class ApodRequestBean {

	@ManagedProperty(value="#{param.date}")
	@Setter
	private String requestedDate;
	
	@ManagedProperty(value="#{apodViewBean}")
    private ApodViewBean apodViewBean;
	
}
