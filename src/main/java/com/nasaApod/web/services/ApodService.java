package com.nasaApod.web.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.nasaApod.Constants;
import com.nasaApod.entities.Apod;

@Service
public class ApodService {

	public static Apod getApod(String stringDate) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Constants.APOD_API)
            .queryParam(Constants.APOD_API_KEY_PARAM_NAME, Constants.APOD_API_KEY_PARAM_VALUE)
            .queryParam(Constants.APOD_API_DATE_PARAM_NAME, stringDate);

        Apod apod = restTemplate.getForObject(builder.toUriString(), Apod.class);
        return apod;
	}
	
}
