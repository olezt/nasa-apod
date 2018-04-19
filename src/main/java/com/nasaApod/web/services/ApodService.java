package com.nasaApod.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.nasaApod.Constants;
import com.nasaApod.entities.Apod;

@ManagedBean(name="apodService")
@SessionScoped
public class ApodService {

	public Apod getApod(String stringDate) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Constants.APOD_API)
            .queryParam(Constants.APOD_API_KEY_PARAM_NAME, Constants.APOD_API_KEY_PARAM_VALUE)
            .queryParam(Constants.APOD_API_DATE_PARAM_NAME, stringDate);

        Apod apod = restTemplate.getForObject(builder.toUriString(), Apod.class);
        return apod;
	}
	
}
