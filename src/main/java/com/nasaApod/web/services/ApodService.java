package com.nasaApod.web.services;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nasaApod.Constants;
import com.nasaApod.entities.Apod;

@Service
public class ApodService {

	public static Apod getApod(Date date) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        Apod apod = restTemplate.getForObject(Constants.APOD_API_WITH_API_KEY, Apod.class);
        return apod;
	}
	
}
