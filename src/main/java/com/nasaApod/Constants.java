package com.nasaApod;

public final class Constants {

	private Constants() {}

	public static final String PARAM_QUESTION_MARK = "?";
	public static final String PARAM_EQUALS = "=";
	
	public static final String APOD_API = "https://api.nasa.gov/planetary/apod";
	public static final String APOD_API_KEY_PARAM_NAME = "api_key";
	public static final String APOD_API_KEY_PARAM_VALUE = "DEMO_KEY";
	
	public static final String APOD_API_WITH_API_KEY = APOD_API+PARAM_QUESTION_MARK+APOD_API_KEY_PARAM_NAME+PARAM_EQUALS+APOD_API_KEY_PARAM_VALUE;
		
}
